package com.example.tvmazeexample.Retrofit
import com.example.tvmazeexample.Retrofit.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object ApiConfig {

    private const val BASE_URL = "https://api.tvmaze.com/"

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(ApiService::class.java)
}