package com.iti.myapplication.data

import androidx.lifecycle.LiveData
import com.iti.myapplication.data.model.Movie
import com.iti.myapplication.data.model.TmdbResponse

interface MovieRepository {
     fun getSavedMovies(): LiveData<MutableList<Movie>>?
     suspend fun saveMovie(movie: Movie?)
     suspend fun deleteMovie(movie: Movie?)
     suspend fun  searchMovies(query: String): TmdbResponse
}