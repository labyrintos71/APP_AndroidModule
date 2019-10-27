package kotlins.module.labyrintos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlins.module.labyrintos.RetrofitForRXJava.GithubService
import kotlins.module.labyrintos.RetrofitForRXJava.GithubResponseModel
import kotlins.module.labyrintos.RetrofitForRXJava.RetrofitCreator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable = CompositeDisposable()
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

        val service = RetrofitCreator.create(GithubService::class.java)
        compositeDisposable.add(
            RetrofitCreator
                .create(GithubService::class.java)
                .getRepoList("discord")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    text.text=it.items[0].full_name
                },{
                    Log.d("MainActivity","ERROR message : ${it.message}")
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}