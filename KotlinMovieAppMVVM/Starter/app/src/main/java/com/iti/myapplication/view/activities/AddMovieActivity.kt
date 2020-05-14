package com.iti.myapplication.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.iti.myapplication.R
import com.iti.myapplication.data.model.Movie
import com.iti.myapplication.view.activities.AddMovieActivity
import com.iti.myapplication.viewmodel.AddViewModel
import com.squareup.picasso.Picasso

class AddMovieActivity : AppCompatActivity() {
    private var titleEditText: EditText? = null
    private var releaseDateEditText: EditText? = null
    private var movieImageView: ImageView? = null
    private var viewModel: AddViewModel? = null
    var posterPath: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
        setupViews()
        viewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)
        /*
    viewModel.getMutableMovieLiveData().observe(this, new Observer<Movie>() {
      @Override
      public void onChanged(Movie movie) {
       // titleEditText.setText(movie.getTitle());
       // releaseDateEditText.setText(movie.getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath()).into(movieImageView);
        //posterPath ="https://image.tmdb.org/t/p/w500/"+data.getStringExtra(EXTRA_POSTER_PATH);
      }
    });
    */
    }

    private fun setupViews() {
        titleEditText = findViewById(R.id.movie_title)
        releaseDateEditText = findViewById(R.id.movie_release_date)
        movieImageView = findViewById(R.id.movie_imageview)
    }

    fun returnToMain() {
        finish()
    }

    fun displayMessage(message: String?) {
        showToast(message)
    }

    fun displayError(message: String?) {
        showToast(message)
    }

    fun showToast(message: String?) {
        Toast.makeText(this@AddMovieActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun goToSearchMovieActivity(view: View?) {
        if (!TextUtils.isEmpty(titleEditText?.getText().toString())) {
            startActivityForResult(Intent(this@AddMovieActivity, SearchActivity::class.java).putExtra(SearchActivity.SEARCH_QUERY, titleEditText?.getText().toString()), SEARCH_MOVIE_ACTIVITY_REQUEST_CODE)
        } else {
            displayError("please enter a movie name to search for")
        }
    }

    fun onClickAddMovie(view: View?) {
        if (!TextUtils.isEmpty(titleEditText?.getText().toString())) {
            val movie = Movie(titleEditText?.getText().toString(), posterPath!!, releaseDateEditText?.getText().toString())
            viewModel?.addMovie(movie)
            displayMessage("movie added successfully")
            returnToMain()
        } else {
            displayError("movie not added")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SEARCH_MOVIE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            titleEditText?.setText(data?.getStringExtra(SearchActivity.Companion.EXTRA_TITLE))
            releaseDateEditText?.setText(data?.getStringExtra(SearchActivity.Companion.EXTRA_RELEASE_DATE))
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + data?.getStringExtra(SearchActivity.Companion.EXTRA_POSTER_PATH)).into(movieImageView)
            posterPath = "https://image.tmdb.org/t/p/w500/" + data?.getStringExtra(SearchActivity.Companion.EXTRA_POSTER_PATH)
        }
    }

    companion object {
        const val SEARCH_MOVIE_ACTIVITY_REQUEST_CODE = 2
    }
}