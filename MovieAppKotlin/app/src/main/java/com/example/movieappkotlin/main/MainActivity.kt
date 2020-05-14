package com.example.movieappkotlin.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappkotlin.R
import com.example.movieappkotlin.add.AddMovieActivity
import com.example.movieappkotlin.model.LocalDataSource
import com.example.movieappkotlin.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MainContract.ViewInterface, AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter
    private lateinit var presenter: MainContract.PresenterInterface
    private lateinit var localDataSource: LocalDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDataSource()
        setupPresenter()
        movies_recyclerview.setLayoutManager(LinearLayoutManager(this))
    }

    private fun setupDataSource() {
         localDataSource = LocalDataSource(application)
    }

    private fun setupPresenter() {
        presenter = MainPresenter(localDataSource, this)
        presenter.getMyMovieList()
    }

    override fun displayMovies(movieList: List<Movie>) {
        if (movieList.size > 0) {
            no_movies_layout.setVisibility(View.INVISIBLE)
            mainAdapter = MainAdapter(movieList)
            movies_recyclerview.setLayoutManager(LinearLayoutManager(this@MainActivity))
            movies_recyclerview.setAdapter(mainAdapter)
            movies_recyclerview.setVisibility(View.VISIBLE)
        } else {
            no_movies_layout.setVisibility(View.VISIBLE)
            movies_recyclerview.setVisibility(View.INVISIBLE)
        }
    }

    override fun displayNoMovies() {
        showNoMovies()
    }

    override fun displayMessage(message: String) {
        showToast(message)
    }


    override fun displayError(message: String) {
        showToast(message)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.let {
            presenter.onDelete(mainAdapter.movieHashSet)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        presenter.getMyMovieList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

    fun showNoMovies() {
        no_movies_layout.visibility = View.VISIBLE
        movies_recyclerview.visibility = View.INVISIBLE
    }
    fun showMovies() {
        no_movies_layout.visibility = View.INVISIBLE
        movies_recyclerview.visibility = View.VISIBLE
    }

    fun showToast(message: String?) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
    fun goToAddMovieActivity(view: View?) {
        startActivity(Intent(this@MainActivity, AddMovieActivity::class.java))
    }
}
