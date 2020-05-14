package com.iti.myapplication.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.iti.myapplication.R
import com.iti.myapplication.data.model.Movie
import com.iti.myapplication.view.adapters.MainAdapter
import com.iti.myapplication.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val moviesRecyclerView: RecyclerView? = null
    private val adapter: MainAdapter? = null
    private var fab: FloatingActionButton? = null
    private val noMoviesLayout: LinearLayout? = null
    private val TAG: String? = "MainActivity"
    private var viewModel: MainViewModel? = null
    private var no_movies_layout: LinearLayout? = null
    private var movies_recyclerview: RecyclerView? = null
    private var mainAdapter: MainAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel?.getMyListMediatorLiveData()?.observe(this, Observer { movies -> displayMovies(movies) })
    }

    private fun initViews() {
        no_movies_layout = findViewById(R.id.no_movies_layout)
        movies_recyclerview = findViewById(R.id.movies_recyclerview)
        fab = findViewById(R.id.fab)
    }

    fun displayMovies(movieList: MutableList<Movie>?) {
        if (movieList != null && movieList.size > 0) {
            no_movies_layout?.setVisibility(View.INVISIBLE)
            mainAdapter = MainAdapter(this@MainActivity)
            movies_recyclerview?.setLayoutManager(LinearLayoutManager(this@MainActivity))
            mainAdapter?.steMovies(movieList)
            movies_recyclerview?.setAdapter(mainAdapter)
            movies_recyclerview?.setVisibility(View.VISIBLE)
        } else {
            no_movies_layout?.setVisibility(View.VISIBLE)
            movies_recyclerview?.setVisibility(View.INVISIBLE)
        }
    }

    fun displayNoMovies() {
        showNoMovies()
    }

    fun displayMessage(message: String?) {
        showToast(message)
    }

    fun displayError(message: String?) {
        showToast(message)
    }

    fun showToast(message: String?) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun showNoMovies() {
        no_movies_layout?.setVisibility(View.VISIBLE)
        movies_recyclerview?.setVisibility(View.INVISIBLE)
    }

    fun showMovies() {
        no_movies_layout?.setVisibility(View.INVISIBLE)
        movies_recyclerview?.setVisibility(View.VISIBLE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AddMovieActivity.Companion.SEARCH_MOVIE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            /*
      if (data != null && data.hasExtra(AddMovieActivity.TitileKey)) {

        // Movie movie = new Movie(data.getStringExtra(AddMovieActivity.TitileKey),data.getStringExtra(AddMovieActivity.PosterPathKey),data.getStringExtra(AddMovieActivity.ReleaseDateKey));
        //localDataSource.insert(movie);
        //Toast.makeText(MainActivity.this,"movie added successfully",Toast.LENGTH_SHORT).show();
        //presenter.
      } else {
        Toast.makeText(MainActivity.this, "movie added successfully", Toast.LENGTH_SHORT).show();
      }
     */
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteMenuItem) {
            // presenter.onDelete(mainAdapter.movieHashSet);
            viewModel!!.deleteMovie(mainAdapter?.movieHashSet)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        //presenter.getMyMoviesList();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater? = menuInflater
        menuInflater?.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun goToAddMovieActivity(view: View?) {
        startActivity(Intent(this@MainActivity, AddMovieActivity::class.java))
    }

    companion object {
        const val ADD_MOVIE_ACTIVITY_REQUEST_CODE = 1
    }
}