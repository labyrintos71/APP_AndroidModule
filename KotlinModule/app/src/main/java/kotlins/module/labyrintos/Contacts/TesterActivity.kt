package kotlins.module.labyrintos.Contacts

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlins.module.labyrintos.R

/**
 * Created by Labyrintos on 2019-11-03
 */

class TesterActivity :AppCompatActivity(){
    //퍼미션 응답 처리 코드
    private val permission_request_code = 100

    private val requiredPermission = arrayOf(
        READ_CONTACTS, WRITE_CONTACTS
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()

    }
    private fun checkPermissions(){
        //API 23 이하일 경우 권한체크 필요없음
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M) return

        //거절당한 권한 목록 가져오기
        val rejectedPermission = requiredPermission.filter {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_DENIED
        }
        
        if(rejectedPermission.isNotEmpty()){
            ActivityCompat.requestPermissions(this,rejectedPermission.toTypedArray(),permission_request_code)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(permission_request_code){
            permission_request_code -> if(grantResults.isNotEmpty()){
                for((i,permission) in permissions.withIndex()){
                    if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                        //권한 거부
                    }
                }
            }
        }
    }
}