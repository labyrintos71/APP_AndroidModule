package kotlins.module.labyrintos.Permission

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
import kotlins.module.labyrintos.R

/**
 * Created by Labyrintos on 2019-11-03
 */
abstract class PermissionActivity : AppCompatActivity() {
    //퍼미션 응답 처리 코드
    private val REQUEST_PERMISSION = 100

    abstract val requiredPermission: Array<String>

    abstract fun doSomething()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(checkPermissions()) doSomething()


    }
     fun checkPermissions() :Boolean{
        //API 23 이하일 경우 권한체크 필요없음
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true

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
            return false
        }
         return true
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
                   showAlertDialog("정상적으로 이용하려면 권한이 필요합니다.")
                }else{
                    doSomething()
                }
            }
        }
    }

    fun showAlertDialog(msg: String) {
        //권한 거부
        AlertDialog.Builder(this).apply {
            setMessage(msg)
            setPositiveButton("설정"){
                _,_->checkPermissions()
            }
        }.show()
    }

}