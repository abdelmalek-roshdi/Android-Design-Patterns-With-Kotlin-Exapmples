package com.example.movieappkotlin.network

import android.app.Application
import androidx.room.Room
import com.example.movieappkotlin.model.LocalDatabase
import com.example.movieappkotlin.model.RetrofitSingletonHolder
import com.example.movieappkotlin.model.SingletonHolder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

public const val api_key = "9ba2a10e20c15ef6fb276c33692ba9e8"
private const val TMDB_BASE_URL = "http://api.themoviedb.org/3/"
private const val TMDB_IMAGEURL = "https://image.tmdb.org/t/p/w500/"

class RetrofitClient {

    companion object: RetrofitSingletonHolder<RetrofitInterface>({
         Retrofit
            .Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RetrofitInterface::class.java)


    })

}