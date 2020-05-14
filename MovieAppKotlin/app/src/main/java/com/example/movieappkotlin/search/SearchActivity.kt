package com.example.movieappkotlin.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappkotlin.R
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.model.RemoteDataSource
import com.example.movieappkotlin.model.TmdbResponse
import kotlinx.android.synthetic.main.activity_search_movie.*

const val SEARCH_QUERY = "searchQuery"
const val EXTRA_TITLE = "SearchActivity.TITLE_REPLY"
const val EXTRA_RELEASE_DATE = "SearchActivity.RELEASE_DATE_REPLY"
const val EXTRA_POSTER_PATH = "SearchActivity.POSTER_PATH_REPLY"
const val SEARCH_MOVIE_ACTIVITY_REQUEST_CODE = 2

class SearchActivity:SearchContract.ViewInterface, AppCompatActivity() {

    private lateinit var query: String
    lateinit var searchPresenter:SearchContract.PresenterInterface
    lateinit var dataSource:RemoteDataSource
    lateinit var adapter:SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        val intent = intent
        query = intent.getStringExtra(SEARCH_QUERY)
        setupDataSource()
        setupPresenter()
        getSearchResults(query)
        search_results_recyclerview.setLayoutManager(LinearLayoutManager(this))

    }

    private fun setupDataSource(){
        dataSource = RemoteDataSource()
    }
    private fun setupPresenter() {
        searchPresenter = SearchPresenter(dataSource, SearchActivity@this)
    }
    private fun getSearchResults(query: String) {
        searchPresenter.getSearchResults(query)
    }


    override fun displayResult(tmdbResponse: TmdbResponse?) {

        tmdbResponse?.let {
            progress_bar.setVisibility(View.INVISIBLE)


            if (tmdbResponse.totalResults == null || tmdbResponse.totalResults == 0) {
                search_results_recyclerview.setVisibility(View.INVISIBLE)
                no_movies_textview.setVisibility(View.VISIBLE)
            } else {
                adapter = SearchAdapter(tmdbResponse.results!!, itemListener)
                search_results_recyclerview.setAdapter(adapter)
                search_results_recyclerview.setVisibility(View.VISIBLE)
                no_movies_textview.setVisibility(View.INVISIBLE)
            }
        }
    }

    override fun displayMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun displayError(message: String) {
        TODO("Not yet implemented")
    }

    private val itemListener: SearchContract.RecyclerItemListener = object : SearchContract.RecyclerItemListener {


        override fun onItemClick(v: View, position: Int) {
            val movie: Movie = adapter.movieList[position]
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_TITLE, movie.title)
            replyIntent.putExtra(EXTRA_RELEASE_DATE, movie.releaseDate)
            replyIntent.putExtra(EXTRA_POSTER_PATH, movie.posterPath)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }
}