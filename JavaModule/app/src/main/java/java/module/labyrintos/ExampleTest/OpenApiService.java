package java.module.labyrintos.ExampleTest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Labyrintos on 2019-10-07
 */
public interface OpenApiService {
    @GET("/api/users/{number}")
    Call<SingleUser> user(@Path("number") long num);

    @GET("/api/users")
    Call<ListUser> users(@Query ("page") long num);
}