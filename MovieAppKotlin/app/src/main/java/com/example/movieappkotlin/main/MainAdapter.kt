package com.example.movieappkotlin.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.R
import com.example.movieappkotlin.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_main.view.*

class MainAdapter(var movieList:List<Movie>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var movieHashSet:HashSet<Movie>
    init {

        this.movieHashSet = HashSet()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_main, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindMovie(position)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movie_imageview: ImageView
        val title_textview: TextView
        var release_date_textview:TextView
        var checkbox: CheckBox
        init {
            movie_imageview = itemView.movie_imageview
            title_textview = itemView.title_textview
            release_date_textview = itemView.release_date_textview
            checkbox = itemView.checkbox
        }

        fun bindMovie(pos: Int){
            title_textview.setText(movieList[pos].title)
            release_date_textview.setText(movieList[pos].releaseDate)
            checkbox.setChecked(false)
            if (movieList[pos].posterPath.isEmpty() || movieList[pos].posterPath.isBlank()) {
                movie_imageview.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_local_movies_gray
                    )
                )
            } else {
                Picasso.get().load(movieList.get(adapterPosition).posterPath)
                    .into(movie_imageview)
            }
            checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton: CompoundButton?, b: Boolean ->
                if (b) {
                    movieHashSet.add(movieList.get(adapterPosition))
                } else {
                    movieHashSet.remove(movieList.get(position))
                }
            })
        }
    }
}