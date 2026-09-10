package com.example.tvmazeexample.Retrofit

import com.example.tvmazeexample.Response.TvShowResponse
import retrofit2.http.GET



interface ApiService {
    @GET("shows")
    suspend fun getShows(): List<TvShowResponse>
}