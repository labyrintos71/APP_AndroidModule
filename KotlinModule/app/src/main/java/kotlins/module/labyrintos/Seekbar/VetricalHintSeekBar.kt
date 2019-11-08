package kotlins.module.labyrintos.Seekbar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import kotlins.module.labyrintos.R
import org.jetbrains.anko.layoutInflater

/**
 * Created by Labyrintos on 2019-11-09
 */
class VetricalHintSeekBar : RelativeLayout {

    private val DISTANCE_MIN = 0
    private val DISTANCE_MAX = 2000
    private val DISTANCE_STEP = 50

    private val hintText by lazy { findViewById<TextView>(R.id.hint_textview) }
    private val verticalSeekbar by lazy { findViewById<VerticalSeekBar>(R.id.vertical_seekbar) }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        addView(context.layoutInflater.inflate(R.layout.item_seekbar_hint, this, false))

        verticalSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateThumb(progress * DISTANCE_STEP)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        //초기값 설정
        Handler().postDelayed({
                verticalSeekbar.max = (DISTANCE_MAX - DISTANCE_MIN) / DISTANCE_STEP
            verticalSeekbar.progress = 1500 / DISTANCE_STEP
            updateThumb(verticalSeekbar.progress * DISTANCE_STEP)
            layoutParams.width = layoutParams.width +hintText.width
            Log.e("1234", "${layoutParams.width}, ${hintText.layoutParams.width}")
        }, 500)
    }

    private fun updateThumb(progress: Int) {
        val thumb = verticalSeekbar.progressDrawable.bounds
        val height =
            thumb.right * (1 - progress.toFloat() / (verticalSeekbar.max * DISTANCE_STEP).toFloat())

        hintText.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        ).apply {
            addRule(RIGHT_OF, verticalSeekbar.id)
            setMargins(0, verticalSeekbar.top + height.toInt(), 10, 0)
            Log.e("123", "${layoutParams.width}, ${hintText.width}")
        }
        hintText.text = "${progress}M"
    }


}