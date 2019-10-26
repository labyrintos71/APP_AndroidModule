package kotlins.module.labyrintos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout{
            editText{
                onClick {

                }
                hint="asdf"

            }
        }
        alert ("다이얼로그 메세지 데숭", "다이얼로그" ){
            yesButton { toast("OK 누름") }
            noButton { toast("cancel을 클릭") }
        }
     //   assert(test.tests>3)
     //   assert(false) {"123"}
     //   throw AssertionError("123")
    }
}
