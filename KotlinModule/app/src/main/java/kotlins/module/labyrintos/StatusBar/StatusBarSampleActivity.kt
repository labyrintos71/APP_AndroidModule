package kotlins.module.labyrintos.StatusBar

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlins.module.labyrintos.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.statusbar_activity.*

class StatusBarSampleActivity : AppCompatActivity() {
    private var defaultFlag: Int = 0
    private var defaultSystemUiVisibility: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statusbar_activity)

        defaultFlag = window.attributes.flags
        defaultSystemUiVisibility = window?.decorView?.systemUiVisibility ?: 0

        color_code.setOnClickListener {
            runBlock {
                window.statusBarColor = Color.RED
            }
        }

        color_theme.setOnClickListener {
            startActivity(Intent(this, ColorActivity::class.java))
        }
        //특징: 상태바 영역을 뷰가 사용한다. 배경색은 변경할 수 없다
        translucent_code.setOnClickListener {
            runBlock {
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
            }
        }

        translucent_theme.setOnClickListener {
            startActivity(Intent(this, TranslucentActivity::class.java))
        }
        //특징: 상태바 영역을 뷰가 사용한다. 상태바가 투명해 진다. 네비게이션바도 투명해지면서 뷰가 해당 영역을 사용한다
        transparent1_code.setOnClickListener {
            runBlock {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )
            }
        }
        //특징: 상태바 영역을 뷰가 사용한다. 배경색을 바꿀 수 있다
        transparent2_code.setOnClickListener {
            runBlock {
                window?.decorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                window.statusBarColor = Color.TRANSPARENT
            }
        }
        //특징: 상태바 영역을 뷰가 사용한다. 배경색을 바꿀 수 있다. 배경색에 따라 상태바 아이콘이 안 보일 수 있는데,
        //상태바 아이콘을 어둡게 만든다. SYSTEM_UI_FLAG_LIGHT_STATUS_BAR는 API레벨 23부터 사용 가능하다
        transparent3_code.setOnClickListener {
            runBlock {
                window?.decorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.TRANSPARENT
            }
        }
    }

    private fun runBlock(block: Activity.() -> Unit) {
        reset()

        block()

        findViewById<View>(android.R.id.content).requestLayout() // you don't need this if you run above codes before setContentView()

    }

    private fun reset() {
        window.attributes.flags = defaultFlag
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, 0)
        window?.decorView?.systemUiVisibility = defaultSystemUiVisibility
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window ?: return
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
