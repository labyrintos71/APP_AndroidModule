package java.module.labyrintos.Retrofit.SingletonExample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Labyrintos on 2019-10-10
 */
public class RetrofitManager {
    private static RetrofitManager ourInstance = new RetrofitManager();
    public static RetrofitManager getInstance() {
        return ourInstance;
    }
    private RetrofitManager() {
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);

    public RetrofitService getService() {
        return service;
    }
}
