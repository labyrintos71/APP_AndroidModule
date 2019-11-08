package kotlins.module.labyrintos.Seekbar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.util.AttributeSet
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
import org.jetbrains.anko.sdk27.coroutines.onSeekBarChangeListener

/**
 * Created by Labyrintos on 2019-11-08
 */
class VerticalSeekBar : AppCompatSeekBar {
    private val DISTANCE_MIN = 0
    private val DISTANCE_MAX = 2000
    private val DISTANCE_STEP = 50

    private val thumbView by lazy {
        LayoutInflater.from(context).inflate(R.layout.item_seekbar_thumb, rootView as ViewGroup, true)
    }
    private val thumbTextView by lazy {
        thumbView.findViewById<TextView>(R.id.thumb_progress_text)
    }
    init {
        setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                updateThumb(progress * DISTANCE_STEP)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        Handler().postDelayed({
            max = (DISTANCE_MAX - DISTANCE_MIN) / DISTANCE_STEP
            progress = 1500 / DISTANCE_STEP
            updateThumb(progress  * DISTANCE_STEP)
        }, 500)
    }

    private fun updateThumb(progress: Int) {
        val thumb = progressDrawable.bounds
        val height = thumb.right * (1 - progress.toFloat() / (max * DISTANCE_STEP).toFloat())

        thumbView.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            addRule(RelativeLayout.LEFT_OF, id)
            setMargins(0, top + height.toInt(), 10, 0)
        }
        thumbTextView.text = "${progress}M"
    }



    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )


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
                progress= (max - max * event.y / height).toInt()
                onSizeChanged(width,height,0,0)
            }
        }
        return true
    }
}