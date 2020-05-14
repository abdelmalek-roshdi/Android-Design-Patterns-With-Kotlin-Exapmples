package com.example.movieappkotlin.search

import android.util.Log
import com.example.movieappkotlin.model.RemoteDataSource
import com.example.movieappkotlin.model.TmdbResponse
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(var remoteDataSource:RemoteDataSource,var searchViewInterface: SearchContract.ViewInterface): SearchContract.PresenterInterface {

    override fun getSearchResults(query: String) {
        remoteDataSource
            .search(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter(Predicate {it.results?.let{ it.size > 0} ?: false})
            .subscribe(Consumer {
                searchViewInterface.displayResult(it)

            }, Consumer {
                searchViewInterface.displayError("Error fetching Movie Data")
            }

            )

    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}