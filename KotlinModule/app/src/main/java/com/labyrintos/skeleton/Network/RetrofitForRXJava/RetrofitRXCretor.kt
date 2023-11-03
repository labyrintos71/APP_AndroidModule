package com.labyrintos.skeleton.Network.RetrofitForRXJava


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by Labyrintos on 2019-10-27
 */


class RetrofitCreator {

    companion object {
        val API_BASE_URL = "https://api.github.com"

        private fun defaultRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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