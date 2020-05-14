package com.iti.myapplication.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.iti.myapplication.R
import com.iti.myapplication.data.model.Movie
import com.iti.myapplication.view.adapters.MainAdapter.MoviesHolder
import com.squareup.picasso.Picasso
import java.util.*

class MainAdapter(var context: Context?) : RecyclerView.Adapter<MoviesHolder?>() {
    var movieList: MutableList<Movie>
    var inflater: LayoutInflater?
    var movieHashSet: HashSet<Movie>?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val view = inflater?.inflate(R.layout.item_movie_main, parent, false)
        return MoviesHolder(view!!)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.title_textview?.setText(movieList.get(position).title)
        holder.release_date_textview?.setText(movieList.get(position).releaseDate)
        holder.checkbox?.setChecked(false)

            Picasso.get().load(movieList[position].posterPath)
                    .into(holder.movie_imageview)

        holder.checkbox?.setOnCheckedChangeListener({ compoundButton: CompoundButton?, b: Boolean ->
            if (b) {
                movieHashSet?.add(movieList.get(position))
            } else {
                movieHashSet?.remove(movieList.get(position))
            }
        })
    }

    fun steMovies(movies: MutableList<Movie>) {
        movieList = movies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movie_imageview: ImageView?
        var title_textview: TextView?
        var release_date_textview: TextView?
        var checkbox: CheckBox?

        init {
            movie_imageview = itemView.findViewById(R.id.main_movie_imageview)
            title_textview = itemView.findViewById(R.id.title_textview)
            release_date_textview = itemView.findViewById(R.id.release_date_textview)
            checkbox = itemView.findViewById(R.id.checkbox)
        }
    }

    init {
        movieList = arrayListOf()
        inflater = LayoutInflater.from(context)
        movieHashSet = HashSet()
    }
}