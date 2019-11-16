package kotlins.module.labyrintos.Seekbar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import kotlins.module.labyrintos.R

/**
 * Created by Labyrintos on 2019-11-08
 */
class VerticalSeekBar : AppCompatSeekBar {
    //TODO onDraw에서 if 체크는 바람직하지 않아보인다.
    // onAttachwindow에서 초기화는 가능하지만 onmeasure 거치기 전이기 때문에 크기가 없어 계산이 안된다.
    // 좀더 좋은 위치는 없는걸까?

    //설정 가능한 attrs 값
    private var MIN = 0
    private var MAX = 100
    private var STEP = 1
    private var INITVAL = 75
    private var MARGIN = 20
    private var DIRECTION = RelativeLayout.LEFT_OF
    private var USE_HINT = false

    private var needInit = true

    private var dockerID: Int = 0
    private lateinit var hintText: SeekBarHintView

    //thumb에 표현할 String 형식
    private var doLambda: (progress: Int) -> String = { progress ->  "$progress" }

    //이벤트 리스너 대체 람다식
    private var progressChanged: (progress: Int) -> Unit = { }
    private var startChanged: (progress: Int) -> Unit = { }
    private var endChanged: (progress: Int) -> Unit = { }

    fun Int.dpToPx(): Int = (this * context.resources.displayMetrics.density).toInt()
    fun Int.pxToDp(): Int = (this / context.resources.displayMetrics.density).toInt()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        getAttrs(attrs, defStyleAttr)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalSeekBar)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.VerticalSeekBar, defStyleAttr, 0)
        setTypeArray(typedArray)
    }

    // 받아온 attrs 값 세팅
    private fun setTypeArray(typedArray: TypedArray) {
        typedArray.apply {
            USE_HINT = getBoolean(R.styleable.VerticalSeekBar_dockerUSE, false)
            dockerID = getResourceId(R.styleable.VerticalSeekBar_dockerID, 0)
            MIN = getInt(R.styleable.VerticalSeekBar_min, 0)
            MAX = getInt(R.styleable.VerticalSeekBar_max, 100)
            STEP = getInt(R.styleable.VerticalSeekBar_step, 1)
            INITVAL = getInt(R.styleable.VerticalSeekBar_init, 75)
            MARGIN = getInt(R.styleable.VerticalSeekBar_dockerMargin, 20)
            DIRECTION = getInt(R.styleable.VerticalSeekBar_dockerDirection, 0)
            recycle()
        }
    }

    private fun setHintView(resID: Int) {
        hintText = (parent as? ViewGroup)?.findViewById(resID)!!
        updateThumb(progress * STEP)

        setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (USE_HINT) updateThumb(progress * STEP)
                progressChanged(progress * STEP)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                startChanged(progress * STEP)
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                endChanged(progress * STEP)
            }
        })

        hintText.setTouchListener {
            val touchy = (it + hintText.top - top) / height
            progress = (max * (1 - touchy)).toInt()
        }
    }

    private fun updateThumb(progress: Int) {
        val thumb = progressDrawable.bounds
        val height =
            thumb.right * (1 - progress.toFloat() / (max * STEP).toFloat())

        hintText.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            addRule(DIRECTION, id)
            //만약 seekbar의 최저점보다 낮다면 더 안내려가게 설정
            val y =
                if (top + height.toInt() < bottom - hintText.height) top + height.toInt() else bottom - hintText.height
            setMargins(MARGIN.dpToPx(), y, MARGIN.dpToPx(), 0)
        }
        hintText.text = doLambda(progress)
    }

    fun setHintLambda(lambda: (progress: Int) -> String) {
        doLambda = lambda
    }

    fun setOnProgressChanged(lambda: (progress: Int) -> Unit) {
        progressChanged = lambda
    }

    fun setOnStartChanged(lambda: (progress: Int) -> Unit) {
        startChanged = lambda
    }

    fun setOnEndChanged(lambda: (progress: Int) -> Unit) {
        endChanged = lambda
    }

    fun getProgressValue(): Int {
        return progress * STEP
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(h, w, oldh, oldw)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            rotate(-90f)
            translate(-height.toFloat(), 0f)
        }
        if (needInit) {
            needInit = false
            max = (MAX - MIN) / STEP
            progress = INITVAL / STEP
            Log.e("setTypeArray:", "$MIN, $MAX, $STEP, $INITVAL")
            Log.e("setTypeArray:", "$max, $progress")
            onSizeChanged(width, height, 0, 0)
            if (USE_HINT) setHintView(dockerID)
        }
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (!isEnabled) return false

        when (event?.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE,
            MotionEvent.ACTION_UP -> {
                progress = (max - max * event.y / height).toInt()
                onSizeChanged(width, height, 0, 0)
                performClick()
            }
        }
        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

}