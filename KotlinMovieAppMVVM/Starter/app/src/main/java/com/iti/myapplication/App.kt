package com.iti.myapplication

import android.app.Application
import com.iti.myapplication.data.db.MovieDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = MovieDatabase.getInstance(this)
    }

    companion object {
        var db: MovieDatabase? = null
    }
}