package kotlins.module.labyrintos.EditText

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * Created by Labyrintos on 2019-11-04
 */
class ClearEditText : AppCompatEditText, TextWatcher, View.OnTouchListener,
    View.OnFocusChangeListener {
    private val drawable: Drawable


    init {
        drawable = DrawableCompat.wrap(
            ContextCompat.getDrawable(
                context,
                androidx.appcompat.R.drawable.abc_ic_clear_material
            )!!
        )
        DrawableCompat.setTintList(drawable, hintTextColors)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        setCl
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, def: Int) : super(context, attrs, def)

    private fun setClearIconVisible(boolean: Boolean){

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
    }

    override fun afterTextChanged(p0: Editable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}