package com.labyrintos.skeleton.AlertDialog

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.labyrintos.skeleton.R

/**
 * Created by Labyrintos on 2019-11-03
 */
class DialogActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showBasicDialog()
    }
    fun showBasicDialog(){
        val listener = DialogInterface.OnClickListener { dialogInterface, i ->
            when(i){
                DialogInterface.BUTTON_POSITIVE ->{

                }
                DialogInterface.BUTTON_NEGATIVE ->{

                }
                DialogInterface.BUTTON_NEUTRAL ->{

                }
            }
        }
        AlertDialog.Builder(this).apply {
            setTitle("다이얼로그 제목")
            setMessage("다이럴로그 내용!")
            setIcon(R.mipmap.ic_launcher)
            setPositiveButton("긍정버튼",listener)
            setNegativeButton("부정버튼"){
                    dialogInterface, i ->  Log.d("showBasicDialog", "something code")
            }
            setNeutralButton("취소버튼",listener)
        }.show()
    }
}