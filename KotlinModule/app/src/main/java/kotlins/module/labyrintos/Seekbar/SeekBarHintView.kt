package kotlins.module.labyrintos.Seekbar

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by Labyrintos on 2019-11-09
 */
class SeekBarHintView : AppCompatTextView {
    lateinit var doLambda: (y: Float) -> Unit
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )
    //터치이벤트 오버라이드시 performclick를 위해 커스텀 뷰 만듬
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        doLambda(event?.y ?: 0f)
        when(event?.action){
            MotionEvent.ACTION_UP->performClick()
        }
        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
    fun setTouchListener(doCalc: (y: Float) -> Unit) {
        doLambda = doCalc
    }
}