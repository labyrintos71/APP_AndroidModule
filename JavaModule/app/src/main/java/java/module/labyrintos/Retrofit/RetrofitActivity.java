package java.module.labyrintos.Retrofit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.module.labyrintos.BuildConfig;
import java.module.labyrintos.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

        //logging-interceptor 사용 예제
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("https://reqres.in/");

        if(BuildConfig.DEBUG) {
            retrofitBuilder.client(client);
        }
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();

/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())        // Gson 처리시
                //.addConverterFactory(ScalarsConverterFactory.create())  // String 등 처리시
                .build();
*/


        OpenApiService openApiService = retrofit.create(OpenApiService.class);
        Call<ListUser> tickerInfo = openApiService.users(2);
        tickerInfo.enqueue(new Callback<ListUser>() {
            @Override
            public void onResponse(Call<ListUser> call, Response<ListUser> response) {
                if(response.body()!=null)
                Toast.makeText(RetrofitActivity.this, response.body().user.get(0).avatar, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ListUser> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //Call<SingleUser> tickerInfo = openApiService.user(2);
        //        tickerInfo.enqueue(new Callback<SingleUser>() {
        //            @Override
        //            public void onResponse(Call<SingleUser> call, Response<SingleUser> response) {
        //                if(response.body()!=null)
        //                Toast.makeText(RetrofitActivity.this, response.body().user.avatar, Toast.LENGTH_SHORT).show();
        //            }
        //            @Override
        //            public void onFailure(Call<SingleUser> call, Throwable t) {
        //                Toast.makeText(RetrofitActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
        //            }
        //        });



    }

}
