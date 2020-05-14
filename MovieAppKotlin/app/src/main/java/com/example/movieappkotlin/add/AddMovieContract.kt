package com.example.movieappkotlin.add

interface AddMovieContract {
    interface PresenterInterface {
        fun addMovie(
            title: String,
            releaseDate: String,
            posterPath: String
        )
    }

    interface ViewInterface {
        fun returnToMain()
        fun displayMessage(message: String)
        fun displayError(message: String)
    }
}