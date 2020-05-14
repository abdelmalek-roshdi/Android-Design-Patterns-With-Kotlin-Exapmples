package com.example.movieappkotlin.model

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(Movie::class), version = 2)
@TypeConverters(IntegerListTypeConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun movieDao():MovieDao
    companion object: SingletonHolder<LocalDatabase, Application>({
        Room.databaseBuilder(it.applicationContext, LocalDatabase::class.java, "movie_database").allowMainThreadQueries().build()
     })

}





