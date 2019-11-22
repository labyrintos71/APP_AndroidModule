package kotlins.module.labyrintos.BottomSheet

import android.app.Dialog
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlins.module.labyrintos.R

/**
 * Created by Labyrintos on 2019-11-19
 */
class BottomSheetSampleDialogFragment :BottomSheetDialogFragment(){
    //   private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
    //        @Override
    //        public void onStateChanged(@NonNull View bottomSheet, int newState) {
    //            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
    //                dismiss();
    //            }
    //        }
    //
    //        @Override
    //        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
    //        }
    //    };
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = View.inflate(context, R.layout.bottomsheet_fragment,null)
        dialog.setContentView(view)
    }
}