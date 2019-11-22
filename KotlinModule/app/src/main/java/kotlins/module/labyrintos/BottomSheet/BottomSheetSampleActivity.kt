package kotlins.module.labyrintos.BottomSheet

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlins.module.labyrintos.R
import kotlinx.android.synthetic.main.bottomsheet_activity.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*
import org.jetbrains.anko.contentView

/**
 * Created by Labyrintos on 2019-11-19
 */
class BottomSheetSampleActivity : AppCompatActivity() {
    private lateinit var bottomsheet: BottomSheetBehavior<NestedScrollView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottomsheet_activity)
        bottomsheet = BottomSheetBehavior.from(bottom_sheet).apply {
            isHideable = true
            peekHeight = 300
            state = BottomSheetBehavior.STATE_COLLAPSED
        }
        bottomsheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        button_1.text = "PEEK"
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        button_1.text = "HIDDEN"

                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        button_1.text = "COLLAPSED"
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        button_1.text = "EXPANDED"
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        button_1.text = "DRAGGING"
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        button_1.text = "SETTLING"
                    }
                }
            }
        })

        button_1.setOnClickListener {
            when (bottomsheet.state) {
                BottomSheetBehavior.STATE_EXPANDED -> {
                    bottomsheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    button_1.text = "HIDE"
                }
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    bottomsheet.state = BottomSheetBehavior.STATE_HIDDEN
                    button_1.text = "EXPANDED"

                }
                BottomSheetBehavior.STATE_HIDDEN -> {
                    bottomsheet.state = BottomSheetBehavior.STATE_EXPANDED
                    button_1.text = "PEEK"

                }
            }
        }

        button_2.setOnClickListener {
            BottomSheetDialog(this).apply {
                setContentView(R.layout.bottomsheet_fragment)
                bottomsheet_text.text="123"
                show()
            }
        }
        button_3.setOnClickListener {
            BottomSheetSampleDialogFragment().apply {
                show(supportFragmentManager,tag)
            }
        }
    }
}