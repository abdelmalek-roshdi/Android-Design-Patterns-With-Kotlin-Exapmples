package com.iti.myapplication.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti.myapplication.data.MovieRepository
import com.iti.myapplication.data.MovieRepositoryImpl
import com.iti.myapplication.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel : ViewModel() {
    var listMediatorLiveData: MediatorLiveData<MutableList<Movie>>? = MediatorLiveData()
    var movieRepository: MovieRepository?
    fun getMyListMediatorLiveData(): MediatorLiveData<MutableList<Movie>>? {
        return listMediatorLiveData
    }

    private fun getAllMovies(): MutableList<Movie>? {
        return movieRepository?.getSavedMovies()?.value
    }

    fun deleteMovie(movies: HashSet<Movie>?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                for (movie in movies!!) movieRepository?.deleteMovie(movie)
            }
        }


    }

    init {
        movieRepository = MovieRepositoryImpl()
        listMediatorLiveData?.postValue(getAllMovies())
        listMediatorLiveData?.addSource(movieRepository?.getSavedMovies()!!, Observer { movies -> listMediatorLiveData?.postValue(movies) })
    }
}