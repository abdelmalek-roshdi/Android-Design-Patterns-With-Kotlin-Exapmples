package com.iti.myapplication.data.net

import com.iti.myapplication.data.model.TmdbResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") apiKey: String?, @Query("query") query: String?): TmdbResponse
}