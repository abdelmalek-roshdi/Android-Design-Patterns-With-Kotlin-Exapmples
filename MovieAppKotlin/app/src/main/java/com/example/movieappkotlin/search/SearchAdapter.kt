package com.example.movieappkotlin.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.R
import com.example.movieappkotlin.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_details.view.*

class SearchAdapter(var movieList: List<Movie>, var listener:SearchContract.RecyclerItemListener): RecyclerView.Adapter<SearchAdapter.SearchMoviesHolder>() {


    inner class SearchMoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView
        val overviewTextView: TextView
        val releaseDateTextView: TextView
        val movieImageView: ImageView

        init {
            titleTextView = itemView.title_textview
            overviewTextView = itemView.overview_overview
            releaseDateTextView = itemView.release_date_textview
            movieImageView = itemView.movie_imageview
            itemView.setOnClickListener { v -> listener.onItemClick(v, adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMoviesHolder {
        return SearchMoviesHolder(LayoutInflater.from(parent.context).inflate( R.layout.item_movie_details, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: SearchMoviesHolder, position: Int) {
        holder.titleTextView.setText(movieList[position].title)
        holder.releaseDateTextView.setText(movieList[position].releaseDate)
        holder.overviewTextView.setText(movieList[position].overview)
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + movieList[position].posterPath)
                .into(holder.movieImageView)

    }
}