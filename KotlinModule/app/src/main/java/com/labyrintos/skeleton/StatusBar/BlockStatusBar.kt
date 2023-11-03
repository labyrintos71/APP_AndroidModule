package com.labyrintos.skeleton.StatusBar

import android.content.Context
import android.os.Build
import android.os.Handler
import java.lang.Exception

/**
 * Created by Labyrintos on 2019-11-22
 */
class BlockStatusBar(val context: Context) {
    companion object {
        val collpaseNotificationHandler = Handler()
    }

    init {
        collpaseNow()
    }
    fun collpaseNow(){

        collpaseNotificationHandler.postDelayed(object : Runnable{
            override fun run() {
                val service = context.getSystemService("statusbar")
                try {
                    val collpaseStatusBar = Class.forName("android.app.StatusBarManager").getMethod("collapsePanels")
                    //  if (Build.VERSION.SDK_INT <=16) collapseStatusBar = statusBarManager .getMethod("collapse");

                    collpaseStatusBar.isAccessible = true

                    collpaseStatusBar.invoke(service)
                    collpaseNotificationHandler.postDelayed(this,100L)

                }catch (e: Exception){
                e.printStackTrace()
            }

            }
        },1L)
    }
}