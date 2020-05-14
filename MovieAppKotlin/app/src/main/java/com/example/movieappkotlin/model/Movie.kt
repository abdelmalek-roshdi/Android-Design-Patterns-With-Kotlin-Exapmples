package com.example.movieappkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movie_table")
class Movie(
    @SerializedName("title") var title: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("release_date") var releaseDate: String
) {
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null

    @SerializedName("video")
    @Expose
    var video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null

    @SerializedName("popularity")
    @Expose
    var popularity: Float? = null

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    var isWatched = false
    val releaseYearFromDate: String
        get() = releaseDate.split("-").toTypedArray()[0]

}