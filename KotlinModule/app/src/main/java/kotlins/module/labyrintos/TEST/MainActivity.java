package kotlins.module.labyrintos.TEST;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kotlins.module.labyrintos.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BottomSheetBehavior mBottomSheetBehavior2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        findViewById(R.id.tester).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "TEST: " );
            }
        });
        final View bottomSheet2 = findViewById(R.id.bottom_sheet2);
        mBottomSheetBehavior2 = BottomSheetBehavior.from(bottomSheet2);
       // mBottomSheetBehavior2.setPeekHeight(300);
        mBottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);

        mBottomSheetBehavior2.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    Log.e(TAG, "STATE_EXPANDED: " );
                }
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    Log.e(TAG, "STATE_COLLAPSED: " );
                }
                else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    Log.e(TAG, "STATE_HIDDEN: " );
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });
    }
}
