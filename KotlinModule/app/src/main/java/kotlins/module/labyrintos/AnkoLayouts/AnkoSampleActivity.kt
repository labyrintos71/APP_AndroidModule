package kotlins.module.labyrintos.AnkoLayouts

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import kotlins.module.labyrintos.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * Created by Labyrintos on 2019-11-19
 */
class AnkoSampleActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout{
           padding = dip(20)

           calendarView {  }.lparams(width = matchParent, height = wrapContent)
           floatingActionButton {
               imageResource = R.drawable.abc_btn_check_material
               onClick { snackbar("안녕!") }
           }.lparams(width = wrapContent, height = wrapContent){
               gravity = Gravity.BOTTOM or Gravity.END
               margin = dip(10)
           }
       }
        alert ("다이얼로그     메세지 데숭", "다이얼로그" ){
             yesButton { toast("OK 누름") }
             noButton { toast("cancel을 클릭") }
         }
    }
}