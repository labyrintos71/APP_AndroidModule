package com.labyrintos.skeleton.Network.RetrofitForRXJava

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Labyrintos on 2019-10-27
 */
interface GithubService {

    @GET("/search/repositories")
    fun getRepoList(@Query("q") query: String): Single<GithubResponseModel>
}