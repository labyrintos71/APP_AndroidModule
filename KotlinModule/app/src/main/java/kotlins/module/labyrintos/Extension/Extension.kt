package kotlins.module.labyrintos.Extension

/**
 * Created by Labyrintos on 2019-11-11
 */

fun Int.dpToPx(): Int = (this * context.resources.displayMetrics.density).toInt()
fun Int.pxToDp(): Int = (this / context.resources.displayMetrics.density).toInt()