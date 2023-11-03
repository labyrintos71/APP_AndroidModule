package com.labyrintos.skeleton.EditText

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
    private var onFocusListener: OnFocusChangeListener? = null
    private var onTouchListener: OnTouchListener? = null

    init {
        drawable = DrawableCompat.wrap(
            ContextCompat.getDrawable(
                context,
                androidx.appcompat.R.drawable.abc_ic_clear_material
            )!!
        )
        DrawableCompat.setTintList(drawable, hintTextColors)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        setClearIconVisible(false)

        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, def: Int) : super(context, attrs, def)

    override fun setOnTouchListener(onTouchListener: OnTouchListener?) {
        this.onTouchListener = onTouchListener
    }

    override fun setOnFocusChangeListener(onFocusChangeListener: OnFocusChangeListener?) {
        this.onFocusListener = onFocusChangeListener
    }

    private fun setClearIconVisible(visible: Boolean) {
        drawable.setVisible(visible, false)
        setCompoundDrawables(null, null, if (visible) drawable else null, null)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        val x = motionEvent?.x?.toInt() ?: 0
        if (drawable.isVisible && x > width - paddingRight - drawable.intrinsicWidth) {
            if (motionEvent?.action == MotionEvent.ACTION_UP) {
                error = null
                text = null
            }
            return true
        }
        return onTouchListener?.onTouch(view, motionEvent) ?: false
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        text?.let {
            setClearIconVisible(if (hasFocus) text.toString().isNotEmpty() else false)
        }

        onFocusListener?.onFocusChange(view, hasFocus)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        text?.let {
            if (isFocused)
                setClearIconVisible(text.isNotEmpty())
        }
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}