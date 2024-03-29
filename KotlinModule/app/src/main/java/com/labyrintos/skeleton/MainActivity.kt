package com.labyrintos.skeleton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import io.reactivex.disposables.CompositeDisposable
import com.labyrintos.skeleton.BottomSheet.BottomSheetSampleActivity
import com.labyrintos.skeleton.StatusBar.BlockStatusBar

class MainActivity : AppCompatActivity() {
    lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_example)
       // seekbar.setHintView(text)
   /*     verticalLayout{
            padding = dip(20)

            calendarView {  }.lparams(width = matchParent, height = wrapContent)
            floatingActionButton {
                imageResource = R.drawable.abc_btn_check_material
                onClick { snackbar("안녕!") }
            }.lparams(width = wrapContent, height = wrapContent){
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(10)
            }
        }*/
       /* alert ("다이얼로그     메세지 데숭", "다이얼로그" ){
            yesButton { toast("OK 누름") }
            noButton { toast("cancel을 클릭") }
        }*/
        startActivity(Intent(this, BottomSheetSampleActivity::class.java))
       //val returnval =  RetrofitCreator.create(ReqResService::class.java).getUser(123)
    }
}