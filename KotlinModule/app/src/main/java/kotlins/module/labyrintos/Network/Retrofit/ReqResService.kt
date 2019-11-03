package kotlins.module.labyrintos.Network.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Labyrintos on 2019-10-27
 */

interface ReqResService  {
    @GET("/api/users{number}")
    fun getUser(@Path("number") num : Long) : Call<SingleUserModel>
}
