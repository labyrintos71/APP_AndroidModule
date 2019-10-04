package java.module.labyrintos.Permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.module.labyrintos.R;

public class PermissionActivity  extends AppCompatActivity {
    private final int REQUEST_PERMISSION = 1999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        //권한 승인이 되어있지 않을경우
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        if (permssionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION);
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this,"이전에 거절한적이 있음",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"신규",Toast.LENGTH_LONG).show();
            }
        }else{
            //do
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한이 허가됨.", Toast.LENGTH_LONG).show();
                    //do
                } else {
                    Toast.makeText(this, "권한 취소", Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
            }

        }
    }
}
