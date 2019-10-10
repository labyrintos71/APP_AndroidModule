package java.module.labyrintos.Retrofit.SingletonExample;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Labyrintos on 2019-10-10
 */
public interface RetrofitService {
    @GET("users/{user}/repos")
    Call<ArrayList<JsonObject>> getListRepos(@Path("user") String id);

}
