package com.labyrintos.skeleton.Extension

import android.content.Context
import java.text.NumberFormat

/**
 * Created by Labyrintos on 2019-11-11
 */

fun Int.dpToPx(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()
fun Int.pxToDp(context: Context): Int = (this / context.resources.displayMetrics.density).toInt()

fun <T> multipleWith(vararg receivers: T, block: T.() -> Unit) {
    receivers.map {
        it.block()
    }
}
fun Double.toMoneyComma(): String = NumberFormat.getInstance().format(this)