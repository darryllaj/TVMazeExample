package com.example.tvmazeexample.Retrofit

import com.example.tvmazeexample.Response.TvShowResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path


interface ApiService {
    @GET("shows")
    suspend fun getShows(): List<TvShowResponse>

    @GET("shows/{id}")
    suspend fun getDetails(
        @Path("id") id: Int
    ): TvShowResponse
}
object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.tvmaze.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService =
        retrofit.create(ApiService::class.java)
}