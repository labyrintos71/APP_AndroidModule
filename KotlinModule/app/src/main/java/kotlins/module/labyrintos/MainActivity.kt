package kotlins.module.labyrintos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import kotlins.module.labyrintos.Retrofit.ReqResService
import kotlins.module.labyrintos.Retrofit.RetrofitCreator
import kotlins.module.labyrintos.Retrofit.SingleUserModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
       /* alert ("다이얼로그 메세지 데숭", "다이얼로그" ){
            yesButton { toast("OK 누름") }
            noButton { toast("cancel을 클릭") }
        }*/

        val service = RetrofitCreator.create(ReqResService::class.java)
        service.getUser(1).enqueue(object : Callback<SingleUserModel>{
            override fun onFailure(call: Call<SingleUserModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<SingleUserModel>,
                response: Response<SingleUserModel>
            ) {
                Log.d("asdf","asfasfd")
                response.body().let {
                    Log.d("asdf","asfasfd2")
                    val data = it
                    text.text="${data?.data?.id}"
                }
            }
        })

     //   assert(test.tests>3)
     //   assert(false) {"123"}
     //   throw AssertionError("123")
    }
}
