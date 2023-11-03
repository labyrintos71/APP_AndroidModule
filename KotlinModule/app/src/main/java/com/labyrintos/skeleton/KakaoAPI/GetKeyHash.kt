package com.labyrintos.skeleton.KakaoAPI

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest

/**
 * Created by Labyrintos on 2019-11-09
 */

class GetKeyHash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 28){
            val info = packageManager.getPackageInfo("com.labyrintos.skeleton", PackageManager.GET_SIGNING_CERTIFICATES)
            for(infos in info.signingInfo.apkContentsSigners){
                val md = MessageDigest.getInstance("SHA")
                md.update(infos.toByteArray())
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        }
    }
}