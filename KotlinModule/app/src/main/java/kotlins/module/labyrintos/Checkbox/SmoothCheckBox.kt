package kotlins.module.labyrintos.Checkbox

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Checkable
import kotlins.module.labyrintos.Extension.dpToPx
import kotlins.module.labyrintos.R
import kotlin.math.*

/**
 * Created by Labyrintos on 2019-11-14
 */
class SmoothCheckBox : View, Checkable {
    private val KEY_INSTANCE = "INSTANCESTATE"
    private val COLOR_TICK = Color.WHITE
    private val COLOR_UNCHECKED = Color.WHITE
    private val COLOR_CHECKED = Color.parseColor("#FB4846")
    private val COLOR_FLOOR_UNCHECKED = Color.parseColor("#DFDFDF")

    private val DRAW_SIZE = 25.dpToPx(context)
    private val ANIM_DURATION = 300
    private val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = checkedColor
        }
    }
    private val tickPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            color = floorColor
        }
    }
    private val floorPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
        }
    }

    private var checkedColor = COLOR_CHECKED
    private var unCheckedColor = COLOR_UNCHECKED
    private var floorColor = COLOR_FLOOR_UNCHECKED
    private var floorUnCheckColor = COLOR_FLOOR_UNCHECKED
    private var isChecked = false
    private var tickDrawing = false
    private var listener: OnCheckedChangeListener? = null
    private var animDuration = ANIM_DURATION
    private var strokeWidth = 0
    private var tickPath = Path()
    private var centerPoint = Point()
    private var tickPoint = arrayOf(Point(), Point(), Point())
    private var floorScale = 1.0f
    private var scaleVal = 1.0f
    private var leftLineDistance = 0f
    private var rightLineDistance = 0f
    private var drewDistance = 0f
    //    private Point[] mTickPoints;
    //    private Point mCenterPoint;
    //    private Path mTickPath;

    private var drawDistance = 0f

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        getAttrs(attrs)
    }

    fun getAttrs(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.SmoothCheckBox).apply {
            tickPaint.color = getColor(R.styleable.SmoothCheckBox_color_tick, COLOR_TICK)
            animDuration = getInt(R.styleable.SmoothCheckBox_duration, ANIM_DURATION)
            floorColor = getColor(
                R.styleable.SmoothCheckBox_color_unchecked_stroke,
                COLOR_FLOOR_UNCHECKED
            )
            checkedColor = getColor(R.styleable.SmoothCheckBox_color_checked, COLOR_CHECKED)
            unCheckedColor = getColor(R.styleable.SmoothCheckBox_color_unchecked, COLOR_UNCHECKED)
            strokeWidth = getDimensionPixelSize(R.styleable.SmoothCheckBox_stroke_width, 0)
            recycle()
        }
        floorUnCheckColor = floorColor
        //리스트뷰 안에 넣을경우 아래 없고 이벤트 처리해주는게 편함
        setOnClickListener {
            toggle()
            tickDrawing = false
            drewDistance = 0f
            if(isChecked) startCheckedAnimation()
            else startUnCheckedAnimation()
        }
        /*setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {0
                toggle();
                mTickDrawing = false;
                mDrewDistance = 0;
                if (isChecked()) {
                    startCheckedAnimation();
                } else {
                    startUnCheckedAnimation();
                }
            }
        });*/
    }


    override fun isChecked(): Boolean {
        return isChecked
    }

    override fun toggle() {
        setChecked(!isChecked)
    }

    override fun setChecked(checked: Boolean) {
        isChecked = checked
        tickDrawing = true
        floorScale = 1.0f
        scaleVal = if (isChecked) 0f else 1f
        floorColor = if (isChecked) checkedColor else floorUnCheckColor
        drewDistance = if (isChecked) leftLineDistance + rightLineDistance else 0f
        invalidate()
        listener?.apply {
            onCheckedChanged(this@SmoothCheckBox, isChecked)
        }
    }

    fun setChecked(checked: Boolean, anim: Boolean) {
        if (anim) {
            tickDrawing = false
            isChecked = checked
            drawDistance = 0f
            if (checked) startCheckedAnimation()
            else startUnCheckedAnimation()
            listener?.apply {
                onCheckedChanged(this@SmoothCheckBox, isChecked)
            }
        } else {
            setChecked(checked)
        }
    }

    private fun measureSize(spec: Int): Int {
        val specSize = MeasureSpec.getSize(spec)

        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED, MeasureSpec.AT_MOST -> {
                min(DRAW_SIZE, specSize)
            }
            MeasureSpec.EXACTLY -> {
                specSize
            }
            else -> 0
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        strokeWidth = if (strokeWidth == 0) measuredWidth / 10 else strokeWidth
        strokeWidth = max(strokeWidth, measuredWidth / 5)
        strokeWidth = max(strokeWidth, 3)
        centerPoint.x = measuredWidth / 2
        centerPoint.y = measuredHeight / 2
        tickPoint[0].x = round(measuredWidth.toFloat() / 30 * 7).toInt()
        tickPoint[0].y = round(measuredWidth.toFloat() / 30 * 14).toInt()
        tickPoint[1].x = round(measuredWidth.toFloat() / 30 * 13).toInt()
        tickPoint[1].y = round(measuredWidth.toFloat() / 30 * 20).toInt()
        tickPoint[2].x = round(measuredWidth.toFloat() / 30 * 22).toInt()
        tickPoint[2].y = round(measuredWidth.toFloat() / 30 * 10).toInt()
        leftLineDistance = sqrt(
            (tickPoint[1].x - tickPoint[0].x).toFloat().pow(2) +
                    (tickPoint[1].y - tickPoint[0].y).toFloat().pow(2)
        )
        rightLineDistance = sqrt(
            (tickPoint[2].x - tickPoint[1].x).toFloat().pow(2) +
                    (tickPoint[2].y - tickPoint[1].y).toFloat().pow(2)
        )
        tickPaint.strokeWidth = strokeWidth.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        drawBorder(canvas)
        drawCenter(canvas)
        drawTick(canvas)
    }

    private fun drawBorder(canvas: Canvas?) {
        floorPaint.color = floorColor
        canvas?.drawCircle(
            centerPoint.x.toFloat(),
            centerPoint.y.toFloat(),
            centerPoint.x * floorScale,
            floorPaint
        )
    }

    private fun drawCenter(canvas: Canvas?) {
        paint.color = unCheckedColor
        val radius = (centerPoint.x - strokeWidth) * scaleVal
        canvas?.drawCircle(centerPoint.x.toFloat(), centerPoint.y.toFloat(), radius, paint)
    }

    private fun drawTick(canvas: Canvas?) {
        if (tickDrawing && isChecked) drawTickPath(canvas)
    }

    private fun drawTickPath(canvas: Canvas?) {
        tickPath.reset()

        if (drewDistance < leftLineDistance) {
            drewDistance += max(measuredWidth / 20f, 3f)
            tickPath.moveTo(tickPoint[0].x.toFloat(), tickPoint[0].y.toFloat())
            tickPath.lineTo(
                tickPoint[0].x + (tickPoint[1].x - tickPoint[0].x) * drewDistance / leftLineDistance,
                tickPoint[0].y + (tickPoint[1].y - tickPoint[0].y) * drewDistance / leftLineDistance
            )
            canvas?.drawPath(tickPath, tickPaint)
            drewDistance = min(drewDistance, leftLineDistance)
        } else {
            tickPath.moveTo(tickPoint[0].x.toFloat(), tickPoint[0].y.toFloat())
            tickPath.lineTo(tickPoint[1].x.toFloat(), tickPoint[1].y.toFloat())
            canvas?.drawPath(tickPath, tickPaint)

            if (drewDistance < leftLineDistance + rightLineDistance) {
                tickPath.reset()
                tickPath.moveTo(tickPoint[1].x.toFloat(), tickPoint[1].y.toFloat())
                tickPath.lineTo(
                    tickPoint[1].x + (tickPoint[2].x - tickPoint[1].x) * (drewDistance - leftLineDistance) / rightLineDistance,
                    tickPoint[1].y + (tickPoint[1].y - tickPoint[2].y) * (drewDistance - leftLineDistance) / rightLineDistance
                )
                canvas?.drawPath(tickPath, tickPaint)
                //  float step = (mWidth / 20) < 3 ? 3 : (mWidth / 20);
                //                mDrewDistance += step;
                drewDistance += max(measuredWidth / 20f, 3f)
            } else {
                tickPath.reset()
                tickPath.moveTo(tickPoint[1].x.toFloat(), tickPoint[1].y.toFloat())
                tickPath.lineTo(tickPoint[2].x.toFloat(), tickPoint[2].y.toFloat())
                canvas?.drawPath(tickPath, tickPaint)
            }

        }
        if (drewDistance < leftLineDistance + rightLineDistance) {
            postDelayed({ postInvalidate() }, 10)
        }
    }

    private fun startCheckedAnimation() {
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = animDuration.toLong() / 3L * 2L
            interpolator = LinearInterpolator()
            addUpdateListener {
                scaleVal = it.animatedValue as Float
                floorColor = getGradientColor(checkedColor, floorUnCheckColor, 1 - scaleVal)
                postInvalidate()
            }
            start()
        }
        ValueAnimator.ofFloat(1f, 0.8f, 1f).apply {
            duration = animDuration.toLong()
            interpolator = LinearInterpolator()
            addUpdateListener {
                floorScale = it.animatedValue as Float
                postInvalidate()
            }
            start()
        }
        postDelayed({
            tickDrawing = true
            postInvalidate()
        }, animDuration.toLong())
    }


    private fun startUnCheckedAnimation() {
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = animDuration.toLong()
            interpolator = LinearInterpolator()
            addUpdateListener {
                scaleVal = it.animatedValue as Float
                floorColor = getGradientColor(checkedColor, floorUnCheckColor, scaleVal)
                postInvalidate()
            }
            start()
        }
        ValueAnimator.ofFloat(1f, 0.8f, 1f).apply {
            duration = animDuration.toLong()
            interpolator = LinearInterpolator()
            addUpdateListener {
                floorScale = it.animatedValue as Float
                postInvalidate()
            }
            start()
        }
    }

    fun getGradientColor(startColor: Int, endColor: Int, percent: Float): Int {
        val startA = Color.alpha(startColor)
        val startR = Color.red(startColor)
        val startG = Color.green(startColor)
        val startB = Color.blue(startColor)

        val endA = Color.alpha(endColor)
        val endR = Color.red(endColor)
        val endG = Color.green(endColor)
        val endB = Color.blue(endColor)

        val currentA = (startA * (1 - percent) + endA * percent).toInt()
        val currentR = (startR * (1 - percent) + endR * percent).toInt()
        val currentG = (startG * (1 - percent) + endG * percent).toInt()
        val currentB = (startB * (1 - percent) + endB * percent).toInt()
        return Color.argb(currentA, currentR, currentG, currentB)
    }

    interface OnCheckedChangeListener {
        fun onCheckedChanged(checkBox: SmoothCheckBox, isChecked: Boolean)
    }
}