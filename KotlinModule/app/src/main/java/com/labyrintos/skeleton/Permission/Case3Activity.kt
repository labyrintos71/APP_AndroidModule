package com.labyrintos.skeleton.Permission

import android.Manifest.permission.*
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.labyrintos.skeleton.R

/**
 * Created by Labyrintos on 2019-11-03
 */
class Case3Activity : AppCompatActivity() {
    //퍼미션 응답 처리 코드
    private val REQUEST_PERMISSION = 100

    private val requiredPermission = arrayOf(
        READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_CONTACTS, WRITE_CONTACTS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()

    }

    fun doSomething(){
        Log.d("TEST", "run run run!!!")
    }

    private fun checkPermissions() {
        //API 23 이하일 경우 권한체크 필요없음
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return

        //거절당한 권한 목록 가져오기
        val rejectedPermission = requiredPermission.filter {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_DENIED
        }

        if (rejectedPermission.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                rejectedPermission.toTypedArray(),
                REQUEST_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (REQUEST_PERMISSION) {
            REQUEST_PERMISSION -> if (grantResults.isNotEmpty()) {
                if(grantResults.filter {
                    it == PackageManager.PERMISSION_DENIED
                }.isEmpty()){
                    //권한 허용됨
                    doSomething()
                }else{
                    // 종료하거나 아래처럼 유도로 처리
                    finish()
                    showAlertDialog("허용 안하면 ~~~ 기능을 수행 못합니다. 허용해주세요")
                }

            }
        }
    }


    fun showAlertDialog(msg: String) {
        val listener = DialogInterface.OnClickListener { _, i ->
            when (i) {
                DialogInterface.BUTTON_POSITIVE -> {
                    checkPermissions()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    finish()
                }
            }
        }
        //권한 거부
        AlertDialog.Builder(this).apply {
            setMessage(msg)
            setPositiveButton("확인", listener)
            setNegativeButton("닫기", listener)
        }.show()
    }

}