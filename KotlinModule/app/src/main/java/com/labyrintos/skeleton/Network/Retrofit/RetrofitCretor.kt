package com.labyrintos.skeleton.Network.Retrofit


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by Labyrintos on 2019-10-27
 */


class RetrofitCreator {

    companion object {
        val API_BASE_URL = "https://reqres.in"

        private fun defaultRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build()
        }

        fun <T> create(service: Class<T>): T {
            return defaultRetrofit().create(service)
        }

        private fun createOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .build()
        }
    }
}