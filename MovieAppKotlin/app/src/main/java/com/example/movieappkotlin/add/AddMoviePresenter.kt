package com.example.movieappkotlin.add

import com.example.movieappkotlin.model.LocalDataSource
import com.example.movieappkotlin.model.Movie

class AddMoviePresenter(var localDataSource: LocalDataSource, var addView:AddMovieContract.ViewInterface) :AddMovieContract.PresenterInterface {

    override fun addMovie(title: String, releaseDate: String, posterPath: String) {
        val movie = Movie(title, posterPath, releaseDate)
        localDataSource.insert(movie)
        addView.displayMessage("movie added successfully")
        addView.returnToMain()
    }
}