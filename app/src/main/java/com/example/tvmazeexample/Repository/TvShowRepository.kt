package com.example.tvmazeexample.Repository

import com.example.tvmazeexample.Response.TvShowResponse
import com.example.tvmazeexample.Retrofit.ApiConfig
import com.example.tvmazeexample.Retrofit.ApiService

class TvShowRepository(
    private val apiService: ApiService
) {

    suspend fun getShow(): List<TvShowResponse>{
        return ApiConfig.apiService.getShows()
    }
    suspend fun getShowDetail(id: Int): TvShowResponse {
        return apiService.getDetails(id)
    }
}