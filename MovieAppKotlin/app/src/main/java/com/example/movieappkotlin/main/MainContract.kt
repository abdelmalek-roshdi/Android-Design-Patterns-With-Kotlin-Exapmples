package com.example.movieappkotlin.main

import com.example.movieappkotlin.model.Movie
import java.util.*

interface MainContract {

    interface PresenterInterface {
        fun getMyMovieList()
        fun onDelete(selectedMovies: HashSet<Movie>)
        fun stop()
    }

    interface ViewInterface {
        fun displayMovies(movieList: List<Movie>)
        fun displayNoMovies()
        fun displayMessage(message: String)
        fun displayError(message: String)
    }
}