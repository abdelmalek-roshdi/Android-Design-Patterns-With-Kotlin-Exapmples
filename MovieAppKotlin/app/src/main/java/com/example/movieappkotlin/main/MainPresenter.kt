package com.example.movieappkotlin.main

import com.example.movieappkotlin.model.LocalDataSource
import com.example.movieappkotlin.model.Movie
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.observers.BasicIntQueueDisposable
import io.reactivex.schedulers.Schedulers
import java.util.HashSet

class MainPresenter(var localDataSource:LocalDataSource, var mainView:MainContract.ViewInterface):MainContract.PresenterInterface {
    var compDisposable: CompositeDisposable
        init {
            compDisposable = CompositeDisposable()
        }
    override fun getMyMovieList() {
        mainView?.let {
           compDisposable.add( localDataSource.allMovies().let {
               it.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(Consumer { it.let {
                       mainView?.displayMovies(it)
                   } })
           })
        }
    }

    override fun onDelete(selectedMovies: HashSet<Movie>) {
        for (movie in selectedMovies){
            localDataSource.delete(movie)
        }

        getMyMovieList()
    }

    override fun stop() {
        if (compDisposable.size() >0 && !compDisposable.isDisposed){
            compDisposable.dispose()
            compDisposable.clear()
        }
    }
}