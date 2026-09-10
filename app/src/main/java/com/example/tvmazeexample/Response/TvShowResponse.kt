package com.example.tvmazeexample.Response

import android.media.Rating
import retrofit2.http.Url

data class TvShowResponse(
    val id: Int,
    val name: String,
    val language: String?,
    val genres: List<String>?,
    val status: String?,
    val runtime: Int?,
    val rating: com.example.tvmazeexample.Response.Rating?,
    val image: Image?,
    val summary: String?,
    val premiered: String?,
    val url: String?
)
data class Rating(
    val average: Double?
)

data class Image(
    val medium: String?,
    val original: String?
)