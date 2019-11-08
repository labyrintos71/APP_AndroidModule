package kotlins.module.labyrintos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    lateinit var compositeDisposable: CompositeDisposable

    private val thumbView by lazy {
        LayoutInflater.from(this).inflate(R.layout.item_seekbar_thumb, null, false)
    }
    private val thumbTextView by lazy {
        thumbView.findViewById<TextView>(R.id.thumb_progress_text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutInflater

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
       // startActivity(Intent(this, ExamActivity::class.java))
       //val returnval =  RetrofitCreator.create(ReqResService::class.java).getUser(123)
    }

}