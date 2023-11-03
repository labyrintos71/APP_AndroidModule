package com.labyrintos.skeleton.StatusBar

import android.content.Intent
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Labyrintos on 2019-11-22
 */
class BlockStatusBarSampleActivity  : AppCompatActivity(){

    //<uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            val closeDialog = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            sendBroadcast(closeDialog)
            BlockStatusBar(this).collpaseNow()
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {

        val Y = event.y.toInt()
        if (Y < 400) {
            onWindowFocusChanged(true)
            //    Toast.makeText(this, "ACTION_DOWN AT COORDS " + "X: " + X + " Y: " + Y, Toast.LENGTH_SHORT).show();
        }
        return true
    }
}