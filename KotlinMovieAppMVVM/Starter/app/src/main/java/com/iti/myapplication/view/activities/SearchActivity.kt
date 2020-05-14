package com.iti.myapplication.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iti.myapplication.R
import com.iti.myapplication.data.model.Movie
import com.iti.myapplication.view.adapters.SearchAdapter
import com.iti.myapplication.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {
    private val TAG: String? = "SearchActivity"
    private var searchResultsRecyclerView: RecyclerView? = null
    private var adapter: SearchAdapter? = null
    private var noMoviesTextView: TextView? = null
    private var progressBar: ProgressBar? = null
    private lateinit var query: String
    private var viewModel: SearchViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        val intent = intent
        query = intent.getStringExtra(SEARCH_QUERY)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        setupViews()
        getSearchResults(query)
        viewModel?.getListLiveData()?.observe(this, Observer { movies -> displayResult(movies)})
    }

    private fun setupViews() {
        searchResultsRecyclerView = findViewById(R.id.search_results_recyclerview)
        noMoviesTextView = findViewById(R.id.no_movies_textview)
        progressBar = findViewById(R.id.progress_bar)
        searchResultsRecyclerView?.setLayoutManager(LinearLayoutManager(this))
    }

    private fun getSearchResults(query: String) {
        viewModel?.searchMovies(query)
    }

    fun displayResult(tmdbResponse: List<Movie>?) {
        progressBar?.setVisibility(View.INVISIBLE)
        if (tmdbResponse == null || tmdbResponse.size == 0) {
            searchResultsRecyclerView?.setVisibility(View.INVISIBLE)
            noMoviesTextView?.setVisibility(View.VISIBLE)
        } else {
            adapter = SearchAdapter(tmdbResponse, this, itemListener)
            searchResultsRecyclerView?.setAdapter(adapter)
            searchResultsRecyclerView?.setVisibility(View.VISIBLE)
            noMoviesTextView?.setVisibility(View.INVISIBLE)
        }
    }

    var itemListener: RecyclerItemListener? = object : RecyclerItemListener {
        override fun onItemClick(v: View?, position: Int) {
            val movie = adapter?.getItemAtPosition(position)
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_TITLE, movie?.title)
            replyIntent.putExtra(EXTRA_RELEASE_DATE, movie?.releaseDate)
            replyIntent.putExtra(EXTRA_POSTER_PATH, movie?.posterPath)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

    interface RecyclerItemListener {
         fun onItemClick(v: View?, position: Int)
    }

    companion object {
        val SEARCH_QUERY: String? = "searchQuery"
        val EXTRA_TITLE: String? = "SearchActivity.TITLE_REPLY"
        val EXTRA_RELEASE_DATE: String? = "SearchActivity.RELEASE_DATE_REPLY"
        val EXTRA_POSTER_PATH: String? = "SearchActivity.POSTER_PATH_REPLY"
    }
}