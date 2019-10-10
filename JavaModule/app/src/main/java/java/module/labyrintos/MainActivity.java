package java.module.labyrintos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.module.labyrintos.Retrofit.RetrofitActivity;
import java.module.labyrintos.Retrofit.SingletonExample.Activity;

/**
 * Created by Labyrintos on 2019-10-01
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, RetrofitActivity.class));
    }

}
