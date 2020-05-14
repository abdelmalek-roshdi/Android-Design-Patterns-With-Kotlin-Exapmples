package com.example.movieappkotlin.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieappkotlin.R
import com.example.movieappkotlin.model.LocalDataSource
import com.example.movieappkotlin.search.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_add_movie.movie_imageview

class AddMovieActivity :AddMovieContract.ViewInterface, AppCompatActivity() {
    private lateinit var dataSource: LocalDataSource
    private lateinit var addMoviePresenter: AddMovieContract.PresenterInterface
    private lateinit var posterPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        setupDataSource()
        setupPresnnter()

    }

    private fun setupDataSource() {
        dataSource = LocalDataSource(application)
    }

    private fun setupPresnnter() {
        addMoviePresenter = AddMoviePresenter(dataSource, this)
    }

    override fun returnToMain() {
        finish()
    }

    override fun displayMessage(message: String) {
        showToast(message)
    }

    override fun displayError(message: String) {
        showToast(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
          super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SEARCH_MOVIE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            movie_title.setText(data!!.getStringExtra(EXTRA_TITLE))
            movie_release_date.setText(data.getStringExtra(EXTRA_RELEASE_DATE))
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + data.getStringExtra(EXTRA_POSTER_PATH))
                .into(movie_imageview)
            posterPath = "https://image.tmdb.org/t/p/w500/" + data.getStringExtra(EXTRA_POSTER_PATH)
        }
    }

    fun showToast(message: String?) {
        Toast.makeText(this@AddMovieActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun onClickAddMovie(view: View?) {
        if (!TextUtils.isEmpty(movie_title.getText().toString())) {
            addMoviePresenter.addMovie(
                movie_title.getText().toString(),
                movie_release_date.getText().toString(),
               posterPath
            )
            displayMessage("movie added successfully")
        } else {
            displayError("movie not added")
        }
    }
    fun goToSearchMovieActivity(view: View?) {
        startActivityForResult(
            Intent(this@AddMovieActivity, SearchActivity::class.java).putExtra(
                SEARCH_QUERY,
                movie_title.getText().toString()
            ), SEARCH_MOVIE_ACTIVITY_REQUEST_CODE
        )
    }
}