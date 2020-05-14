package com.iti.myapplication.data.model

import androidx.lifecycle.LiveData
import com.iti.myapplication.App
import com.iti.myapplication.data.db.MovieDao

class LocalDataSource {
    private val movieDao: MovieDao?
    fun allMovies(): LiveData<MutableList<Movie>>? {
        return movieDao?.getAll()
    }

    fun insert(movie: Movie?) {
        movieDao?.insert(movie)
    }

    fun delete(movie: Movie?) {
        movieDao?.delete(movie?.id)
    }

    init {
        movieDao = App.db?.movieDao()
    }
}