package com.labyrintos.skeleton.Network.RetrofitForRXJava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.labyrintos.skeleton.R

/**
 * Created by Labyrintos on 2019-10-31
 */
class ExamActivity : AppCompatActivity() {
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
                    //text.text=it.items[0].full_name
                },{
                    Log.d("MainActivity","ERROR message : ${it.message}")
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}