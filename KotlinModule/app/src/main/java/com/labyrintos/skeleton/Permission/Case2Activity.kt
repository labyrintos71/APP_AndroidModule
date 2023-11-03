package com.labyrintos.skeleton.Permission

import android.Manifest.permission.*
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.labyrintos.skeleton.R

/**
 * Created by Labyrintos on 2019-11-03
 */
class Case2Activity : AppCompatActivity() {
    //퍼미션 응답 처리 코드
    private val REQUEST_PERMISSION = 100

    private val requiredPermission = arrayOf(
        READ_CALENDAR, WRITE_CALENDAR, READ_CONTACTS, WRITE_CONTACTS, CALL_PHONE, READ_CALL_LOG
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()

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
                if(grantResults.filter { it == PackageManager.PERMISSION_DENIED }.isNotEmpty()){
                    AlertDialog.Builder(this).apply {
                        setMessage("캘린더 권한을 거부하시면 `~~ 앱을 사용할 수 없습니다. 권한을 허용해 주십시오.")
                        setPositiveButton("확인"){_,_ ->
                            checkPermissions()
                        }
                    }.show()
                }
            }
        }
    }

    fun showAlertDialog(msg: String) {
        val uri = Uri.fromParts("package", packageName, null)
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri
        )
        val listener = DialogInterface.OnClickListener { _, i ->
            when (i) {
                DialogInterface.BUTTON_POSITIVE -> {
                    startActivityForResult(intent, REQUEST_PERMISSION)
                }
            }
        }
        //권한 거부
        AlertDialog.Builder(this).apply {
            setMessage(msg)
            setPositiveButton("설정", listener)
            setNegativeButton("닫기", listener)
        }.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_PERMISSION -> checkPermissions()
        }
    }
}