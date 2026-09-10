package com.example.tvmazeexample.Repository

import com.example.tvmazeexample.Response.TvShowResponse
import com.example.tvmazeexample.Retrofit.ApiConfig

class TvShowRepository {
    suspend fun getShow(): List<TvShowResponse>{
        return ApiConfig.apiService.getShows()
    }
}