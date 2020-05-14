package com.example.movieappkotlin.model

import android.app.Application
import io.reactivex.Single

class LocalDataSource(application: Application) {
     val db:LocalDatabase by lazy {
           LocalDatabase.getInstance(application)
     }
    val  movieDao:MovieDao by lazy {
            db.movieDao()
    }

    fun allMovies(): Single<List<Movie>> {
        return movieDao.getAll()
    }

    fun insert(movie: Movie?) {
        movieDao.insert(movie!!)
    }

    fun delete(movie: Movie) {
        movie.id?.let { movieDao.delete(it) }
    }

    fun update(movie: Movie?) {
        movieDao.update(movie!!)
    }
}