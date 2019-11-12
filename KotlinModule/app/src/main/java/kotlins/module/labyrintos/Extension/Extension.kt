package kotlins.module.labyrintos.Extension

import android.content.Context

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