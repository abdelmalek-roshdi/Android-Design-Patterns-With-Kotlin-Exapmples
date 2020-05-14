package com.iti.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti.myapplication.data.MovieRepository
import com.iti.myapplication.data.MovieRepositoryImpl
import com.iti.myapplication.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddViewModel : ViewModel() {
    // MutableLiveData<Movie> mutableMovieLiveData = new MutableLiveData<>();
    var movieRepository: MovieRepository?
    fun addMovie(movie: Movie?) {
       viewModelScope.launch {
           withContext(Dispatchers.IO){
               movieRepository?.saveMovie(movie)
           }
       }
        //    mutableMovieLiveData.postValue(movie);
    } // public MutableLiveData<Movie> getMutableMovieLiveData() {

    //   return mutableMovieLiveData;
    // }
    init {
        movieRepository = MovieRepositoryImpl()
    }
}