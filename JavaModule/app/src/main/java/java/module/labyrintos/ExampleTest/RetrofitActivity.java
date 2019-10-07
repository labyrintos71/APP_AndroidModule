package java.module.labyrintos.ExampleTest;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.module.labyrintos.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Labyrintos on 2019-10-07
 */
public class RetrofitActivity extends AppCompatActivity {
    String TAG = RetrofitActivity.class.getSimpleName();
    //https://reqres.in/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())        // Gson 처리시
                //.addConverterFactory(ScalarsConverterFactory.create())  // String 등 처리시
                .build();
        OpenApiService openApiService = retrofit.create(OpenApiService.class);
        Call<SingleUser> tickerInfo = openApiService.user(2);
        tickerInfo.enqueue(new Callback<SingleUser>() {
            @Override
            public void onResponse(Call<SingleUser> call, Response<SingleUser> response) {
                if(response.body()!=null)
                Toast.makeText(RetrofitActivity.this, response.body().user.avatar, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<SingleUser> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
