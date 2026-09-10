package com.example.tvmazeexample.Repository

import com.example.tvmazeexample.Response.TvShowResponse

interface TvShowRepositoryInterface {

    suspend fun getShow(): List<TvShowResponse>

    suspend fun getShowDetail(id: Int): TvShowResponse
}