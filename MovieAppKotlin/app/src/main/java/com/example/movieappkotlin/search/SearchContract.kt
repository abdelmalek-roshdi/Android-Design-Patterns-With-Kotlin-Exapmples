package com.example.movieappkotlin.search

import android.view.View
import com.example.movieappkotlin.model.TmdbResponse

interface SearchContract {
    interface PresenterInterface {
        fun getSearchResults(query: String)
        fun stop()
    }

    interface ViewInterface {
        fun displayResult(tmdbResponse: TmdbResponse?)
        fun displayMessage(message: String)
        fun displayError(message: String)
    }

     interface RecyclerItemListener {
        fun onItemClick(v: View, position: Int)
    }
}
