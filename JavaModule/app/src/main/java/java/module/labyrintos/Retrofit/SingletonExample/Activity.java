package java.module.labyrintos.Retrofit.SingletonExample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import java.module.labyrintos.R;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Labyrintos on 2019-10-10
 */
public class Activity  extends AppCompatActivity {
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText =  findViewById(R.id.edittext);
        textView =  findViewById(R.id.textview);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch();
            }
        });
    }

    public void onSearch() {
        String id = editText.getText().toString();

        if (!id.isEmpty()) {
            Call<ArrayList<JsonObject>> res = RetrofitManager.getInstance().getService().getListRepos(id);
            res.enqueue(new Callback<ArrayList<JsonObject>>() {
                @Override
                public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                    Log.d("Retrofit", response.toString());
                    if (response.body() != null)
                        textView.setText(response.body().toString());
                }

                @Override
                public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                    Log.e("Err", t.getMessage());
                }
            });
        } else
            Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
    }
}
