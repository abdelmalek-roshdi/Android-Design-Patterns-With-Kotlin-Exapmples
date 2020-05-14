package com.iti.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.iti.myapplication.data.MovieRepository
import com.iti.myapplication.data.MovieRepositoryImpl
import com.iti.myapplication.data.model.Movie
import com.iti.myapplication.data.model.TmdbResponse
import kotlinx.coroutines.*

class SearchViewModel : ViewModel() {
    var movieRepository: MovieRepository?

    init {
        movieRepository = MovieRepositoryImpl()
    }

    var myListLiveData: LiveData<MutableList<Movie>>? = null
    fun searchMovies(query: String): LiveData<MutableList<Movie>>? {
        myListLiveData = liveData(Dispatchers.IO) {
            var liveData:TmdbResponse? = null

            runCatching {
                  liveData  = movieRepository?.searchMovies(query)
                }
                emit(liveData?.results!!.toMutableList())

        }

//        viewModelScope.launch {
//            var liveData:LiveData<MutableList<Movie>>? = null
//            val task = async{
//                liveData  = movieRepository?.searchMovies(query)
//            }
//            task.await()
//            myListLiveData = liveData
//        }
//        viewModelScope.launch {
//            //var liveData:LiveData<MutableList<Movie>>? = null
//
//               // liveData = movieRepository?.searchMovies(query)
//
//
//
//            myListLiveData = movieRepository?.searchMovies(query)
//        }

        return myListLiveData
    }

    fun getListLiveData(): LiveData<MutableList<Movie>>? {
        return myListLiveData
    }


}