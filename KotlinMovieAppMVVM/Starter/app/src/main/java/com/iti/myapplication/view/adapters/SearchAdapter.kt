package com.iti.myapplication.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iti.myapplication.R
import com.iti.myapplication.data.model.Movie
import com.iti.myapplication.view.activities.SearchActivity.RecyclerItemListener
import com.iti.myapplication.view.adapters.SearchAdapter.SearchMoviesHolder
import com.squareup.picasso.Picasso

class SearchAdapter(
        private val movieList: List<Movie>?, private val context: Context?, private val listener: RecyclerItemListener?) : RecyclerView.Adapter<SearchMoviesHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMoviesHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie_details, parent, false)
        val viewHolder = SearchMoviesHolder(view)
        view.setOnClickListener { v: View? -> listener?.onItemClick(v, viewHolder.adapterPosition) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchMoviesHolder, position: Int) {
        holder.titleTextView?.setText(movieList?.get(position)?.title)
        holder.releaseDateTextView?.setText(movieList?.get(position)?.releaseYearFromDate)
        holder.overviewTextView?.setText(movieList?.get(position)?.overview)

            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + movieList?.get(position)?.posterPath)
                    .into(holder.movieImageView)

    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    fun getItemAtPosition(pos: Int): Movie? {
        return movieList?.get(pos)
    }

     inner class SearchMoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val titleTextView: TextView?
         val overviewTextView: TextView?
         val releaseDateTextView: TextView?
         val movieImageView: ImageView?

        init {
            titleTextView = itemView.findViewById(R.id.title_textview)
            overviewTextView = itemView.findViewById(R.id.overview_overview)
            releaseDateTextView = itemView.findViewById(R.id.release_date_textview)
            movieImageView = itemView.findViewById(R.id.movie_imageview)
            itemView.setOnClickListener { v -> listener?.onItemClick(v, adapterPosition) }
        }
    }

}