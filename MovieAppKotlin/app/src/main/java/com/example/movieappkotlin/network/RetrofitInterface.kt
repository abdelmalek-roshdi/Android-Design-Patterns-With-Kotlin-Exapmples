package com.example.movieappkotlin.network

import com.example.movieappkotlin.model.TmdbResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey:String, @Query("query") query:String): Single<TmdbResponse?>
}