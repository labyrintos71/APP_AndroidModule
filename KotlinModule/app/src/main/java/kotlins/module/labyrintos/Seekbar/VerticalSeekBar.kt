package kotlins.module.labyrintos.Seekbar

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import kotlins.module.labyrintos.R
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.sdk27.coroutines.onSeekBarChangeListener

/**
 * Created by Labyrintos on 2019-11-08
 */
class VerticalSeekBar : AppCompatSeekBar {

    private var MIN = 0
    private var MAX = 100
    private var STEP = 1
    private var INITVAL = 75
    private var MARGIN = 20
    private var DIRECTION = RelativeLayout.LEFT_OF
    private var USE_HINT = false

    private var dockerID : Int =0
    private lateinit var hintText: SeekBarHintView
    private var doLambda: (progress:Int) -> String = { "$progress" }

    fun Int.dpToPx(): Int = (this * context.resources.displayMetrics.density).toInt()
    fun Int.pxToDp(): Int = (this / context.resources.displayMetrics.density).toInt()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttrs(attrs, defStyleAttr)
    }

    // 만들어 놓은 attrs을 참조합니다.
    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalSeekBar)
        setTypeArray(typedArray)
    }

    // 만들어 놓은 attrs을 참조합니다.
    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalSeekBar, defStyleAttr, 0)
        setTypeArray(typedArray)
    }

    // 여기서 값을 세팅해줍니다.
    private fun setTypeArray(typedArray: TypedArray) {
        USE_HINT = typedArray.getBoolean(R.styleable.VerticalSeekBar_dockerUSE, false)
        dockerID = typedArray.getResourceId(R.styleable.VerticalSeekBar_dockerID, 0)
        MIN = typedArray.getInt(R.styleable.VerticalSeekBar_MIN, 0)
        MAX = typedArray.getInt(R.styleable.VerticalSeekBar_MAX, 100)
        STEP = typedArray.getInt(R.styleable.VerticalSeekBar_STEP, 1)
        INITVAL = typedArray.getInt(R.styleable.VerticalSeekBar_INITVAL, 75)
        MARGIN = typedArray.getInt(R.styleable.VerticalSeekBar_dockerMargin, 20)
        DIRECTION = typedArray.getInt(R.styleable.VerticalSeekBar_dockerDirection, 0)

        Handler().postDelayed({
            if(USE_HINT) setHintView(dockerID)
        }, 500)
        typedArray.recycle()
    }

    private fun setHintView(resID: Int) {
        hintText = (parent as? ViewGroup)?.findViewById(resID)!!

        Handler().postDelayed({
            max = (MAX - MIN) / STEP
            progress = INITVAL / STEP
            updateThumb(progress * STEP)
        }, 500)

        setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(USE_HINT)
                updateThumb(progress * STEP)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
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

    fun setHintLambda(lambda: (progress:Int) -> String){
        doLambda = lambda
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