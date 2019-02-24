package com.project.jogjatour.data.api

import com.project.jogjatour.data.Constants
import com.project.jogjatour.data.DestinationResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("bootcamp/jsonBootcamp.php")
    fun getData(): Observable <DestinationResponse>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.base_url)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }


        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
}