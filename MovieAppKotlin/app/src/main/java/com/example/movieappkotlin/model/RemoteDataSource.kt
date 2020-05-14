package com.example.movieappkotlin.model

import com.example.movieappkotlin.network.RetrofitClient
import com.example.movieappkotlin.network.api_key
import io.reactivex.Single
import retrofit2.Call
class RemoteDataSource {

    fun search(query: String): Single<TmdbResponse?> {
        return RetrofitClient
            .getInstance()
            .searchMovie(api_key, query)
    }
}