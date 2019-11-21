package kotlins.module.labyrintos.Toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import kotlins.module.labyrintos.R
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Created by Labyrintos on 2019-11-20
 */
class CenterTitleToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyle: Int = R.attr.toolbarStyle) : Toolbar(context, attrs, defStyle) {

    private var location: IntArray = IntArray(2)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        //id 인자로 받게 해줘야됨
        /*rootView.title_text?.apply{
            getLocationInWindow(location)
            translationX += (-location[0] + this@CenterTitleToolbar.width / 2 - width / 2);
        }*/
    }
}