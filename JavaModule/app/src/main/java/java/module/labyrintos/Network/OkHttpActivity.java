package java.module.labyrintos.Network;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.module.labyrintos.R;

/**
 * Created by Labyrintos on 2019-10-07
 */
public class OkHttpActivity extends AppCompatActivity {
    String TAG = OkHttpActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //send();
    }
    /*
    private void send(){
        new Thread(){
            @Override
            public void run() {
                getSync("https://reqres.in/api/users?page=2");
                super.run();
            }
        }.start();
    }
    // 동기 처리를 하고자 한다면 execute를 사용하면 되고 비동기 처리를 원한다면 enqueue를 사용하면 됩니다.

    //[GET 동기]
    public void getSync(String requestURL) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
          //          .addHeader("x-api-key", RestTestCommon.API_KEY)
                    .url(requestURL)
                    .build(); //GET Request

            //동기 처리시 execute함수 사용
            Response response = client.newCall(request).execute();

            //출력
            String message = response.body().string();
            Log.d(TAG, "getSync: "+message);
        } catch (Exception e){
            Log.d(TAG, "getSync: "+e.toString());
        }
    }

    //[GET 비동기]
    public void get(String requestURL, Callback callback) {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
         //           .addHeader("x-api-key", RestTestCommon.API_KEY)
                    .url(requestURL)
                    .build();

            //비동기 처리 (enqueue 사용)
            client.newCall(request).enqueue(callback);

        } catch (Exception e){
            System.err.println(e.toString());
        }
    }

    //[POST 동기]
    public void postSync(String requestURL, String jsonMessage) {
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                  //  .addHeader("x-api-key", RestTestCommon.API_KEY)
                    .url(requestURL)
                    .post(RequestBody.create(MediaType.parse("application/json"), jsonMessage)) //POST로 전달할 내용 설정
                    .build();

            //동기 처리시 execute함수 사용
            Response response = client.newCall(request).execute();

            //출력
            String message = response.body().string();
            System.out.println(message);

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    //[POST 비동기]
    public void post(String requestURL, String message) {

        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                   // .addHeader("x-api-key", RestTestCommon.API_KEY)
                    .url(requestURL)
                    .post(RequestBody.create(MediaType.parse("application/json"), message))
                    .build();

            //비동기 처리 (enqueue 사용)
            client.newCall(request).enqueue(new Callback() {
                //비동기 처리를 위해 Callback 구현
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("error + Connect Server Error is " + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println("Response Body is " + response.body().string());
                }
            });

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

*/
}
