package com.iti.myapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.iti.myapplication.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    open fun getAll(): LiveData<MutableList<Movie>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun insert(movie: Movie?)

    @Query("DELETE FROM movie_table WHERE id = :id")
    open fun delete(id: Int?)

    @Query("DELETE FROM movie_table")
    open fun deleteAll()



    @Update
    open fun updateMovie(movie: Movie?)
}