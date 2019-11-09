package kotlins.module.labyrintos.Seekbar

import android.content.Context
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

    private val MIN = 0
    private val MAX = 2000
    private val STEP = 50
    private val INITVAL = 1500
    private val MARGIN = 20
    private val DIRECTION = RelativeLayout.LEFT_OF

    private lateinit var hintText: SeekBarHintView

    fun Int.dpToPx(): Int = (this * context.resources.displayMetrics.density).toInt()
    fun Int.pxToDp(): Int = (this / context.resources.displayMetrics.density).toInt()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    fun getAttrs(attrs: AttributeSet){
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.VerticalSeekBar)

    }
    fun setHintView(view: SeekBarHintView) {
        hintText = view

        Handler().postDelayed({
            max = (MAX - MIN) / STEP
            progress = INITVAL / STEP
            updateThumb(progress * STEP)
        }, 500)

        setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
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
            setMargins(MARGIN, y, MARGIN, 0)

            Log.e("123", "${layoutParams.width}, ${hintText.width}")
        }
        hintText.text = "${progress}M"
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