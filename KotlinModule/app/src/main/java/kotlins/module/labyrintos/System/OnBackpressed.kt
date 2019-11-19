package kotlins.module.labyrintos.System

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlins.module.labyrintos.R
import kotlin.system.exitProcess

/**
 * Created by Labyrintos on 2019-11-19
 */
class OnBackpressed : AppCompatActivity(){
    private var backpressedTime = -1000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backpressedTime <= 1000){
            finishAffinity()
            exitProcess(0)
        }

        backpressedTime = System.currentTimeMillis()
        Toast.makeText(this, "이전 버튼을 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
        //super.onBackPressed()
    }
}