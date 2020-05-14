package com.iti.myapplication.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iti.myapplication.data.model.Movie

@Database(entities = [Movie::class], version = 2, exportSchema = true)
@TypeConverters(GenreIdConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao?

    companion object {
        private val DB_NAME: String = "movie_database"
        private var INSTANCE: MovieDatabase? = null
        fun getInstance(application: Application): MovieDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(application, MovieDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE
        }
    }
}