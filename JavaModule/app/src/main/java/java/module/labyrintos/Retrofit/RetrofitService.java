package java.module.labyrintos.Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Labyrintos on 2019-10-07
 */
public interface RetrofitService {
    @GET("/api/users/{number}")
    Call<SingleUser> user(@Path("number") long num);

    @GET("/api/users")
    Call<ListUser> users(@Query ("page") long num);

    @FormUrlEncoded
    @PUT("/api/users/{id}")
    Call<PutData> updateJob(@Path("id") long id, @Field("name") String name, @Field("job") String job);

    @FormUrlEncoded
    @POST("/api/login")
    Call<PostData> doLogin(@Field("email") String email, @Field("password") String password);
}