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
class Case1Activity : AppCompatActivity() {
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
                for ((i, permission) in permissions.withIndex()) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        when (permission) {
                            //READ/WRITE의 경우 한번에 같이 물어보기 때문에 하나만 적어줘도 된다.
                            //권한 반복
                            READ_CALENDAR -> {
                                AlertDialog.Builder(this).apply {
                                    setMessage("캘린더 권한을 거부하시면 `~~ 앱을 사용할 수 없습니다. 권한을 허용해 주십시오.")
                                    setPositiveButton("확인",
                                        object : DialogInterface.OnClickListener {
                                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                                checkPermissions()
                                            }
                                        })
                                }.show()
                            }
                            //설정 유도
                            READ_CONTACTS -> {
                                showAlertDialog(
                                    """
                                    전화번호부 기능을 거부 하시면 ~~~와 같은 기능을 사용하실 수 없습니다.
                                    
                                    [설정] -> 권한 에 가셔서 기능 허용 바랍니다.
                                """.trimIndent()
                                )
                            }

                        }
                    }
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