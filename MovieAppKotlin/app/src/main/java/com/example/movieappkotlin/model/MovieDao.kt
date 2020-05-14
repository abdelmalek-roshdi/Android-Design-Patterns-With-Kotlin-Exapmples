package com.example.movieappkotlin.model

import androidx.room.*
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAll(): Single<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("DELETE FROM movie_table WHERE id = :id")
    fun delete(id:Int)

    @Query("DELETE FROM movie_table")
    fun deleteAll()

    @Update
    fun update(movie: Movie)
}