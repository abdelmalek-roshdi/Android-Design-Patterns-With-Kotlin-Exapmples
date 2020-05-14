package com.iti.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieappkotlin.network.RetrofitClient
import com.iti.myapplication.App
import com.iti.myapplication.data.model.Movie
import com.iti.myapplication.data.model.TmdbResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl : MovieRepository {

    private val allMovies: MutableLiveData<MutableList<Movie>> = MutableLiveData()
    override fun getSavedMovies(): LiveData<MutableList<Movie>>? {
        return App.db?.movieDao()?.getAll()
    }

    override suspend fun saveMovie(movie: Movie?) {
        App.db?.movieDao()?.insert(movie)
    }

    override suspend fun deleteMovie(movie: Movie?) {
        App.db?.movieDao()?.delete(movie?.id)
    }

    override suspend fun searchMovies(query: String): TmdbResponse {
        return  RetrofitClient.getInstance().searchMovie(API_KEY,query)
    }

    companion object {
        val API_KEY: String? = "9ba2a10e20c15ef6fb276c33692ba9e8"
    }
}

