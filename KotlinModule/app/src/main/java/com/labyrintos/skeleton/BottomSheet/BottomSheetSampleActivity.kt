package com.labyrintos.skeleton.BottomSheet

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.labyrintos.skeleton.R
import com.labyrintos.skeleton.databinding.BottomsheetActivityBinding
import com.labyrintos.skeleton.databinding.BottomsheetFragmentBinding

/**
 * Created by Labyrintos on 2019-11-19
 */
class BottomSheetSampleActivity : AppCompatActivity() {
    private lateinit var bottomsheet: BottomSheetBehavior<NestedScrollView>
    private lateinit var binding: BottomsheetActivityBinding
    private lateinit var bindingF: BottomsheetFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BottomsheetActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomsheet = BottomSheetBehavior.from(binding.bottomSheet).apply {
            isHideable = true
            peekHeight = 300
            state = BottomSheetBehavior.STATE_COLLAPSED
        }
        bottomsheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.button1.text = "PEEK"
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.button1.text = "HIDDEN"

                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.button1.text = "COLLAPSED"
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        binding.button1.text = "EXPANDED"
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        binding.button1.text = "DRAGGING"
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        binding.button1.text = "SETTLING"
                    }
                }
            }
        })

        binding.button1.setOnClickListener {
            when (bottomsheet.state) {
                BottomSheetBehavior.STATE_EXPANDED -> {
                    bottomsheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    binding.button1.text = "HIDE"
                }
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    bottomsheet.state = BottomSheetBehavior.STATE_HIDDEN
                    binding.button1.text = "EXPANDED"

                }
                BottomSheetBehavior.STATE_HIDDEN -> {
                    bottomsheet.state = BottomSheetBehavior.STATE_EXPANDED
                    binding.button1.text = "PEEK"

                }
            }
        }

        binding.button2.setOnClickListener {
            BottomSheetDialog(this).apply {
                setContentView(R.layout.bottomsheet_fragment)
                bindingF.bottomsheetText.text="123"
                show()
            }
        }
        binding.button3.setOnClickListener {
            BottomSheetSampleDialogFragment().apply {
                show(supportFragmentManager,tag)
            }
        }
    }
}