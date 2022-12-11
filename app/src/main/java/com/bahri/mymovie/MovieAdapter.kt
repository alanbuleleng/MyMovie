package com.bahri.mymovie

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(private var  listMovie: ArrayList<ResultsItem>):
    RecyclerView.Adapter<MovieAdapter.myviewHolder>() {
    class myviewHolder (itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image : ImageView = itemview.findViewById(R.id.image_tm)
        val judul : TextView = itemview.findViewById(R.id.tv_judul)
        val path : TextView = itemview.findViewById(R.id.tv_backdrop_path)
        val league : TextView = itemview.findViewById(R.id.original_language)
        val date : TextView = itemview.findViewById(R.id.tv_release_date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trending_movie,parent,false)
        return myviewHolder(view)
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        val data = listMovie[position]
        Glide.with(holder.itemView)
            .load("https://www.themoviedb.org/t/p/w220_and_h330_face${data.posterPath}")
            .apply(RequestOptions.overrideOf(150,150)).into(holder.image)
        holder.judul.text = data.title
        holder.path.text = data.backdropPath
        holder.league.text = data.originalLanguage
        holder.date.text = data.releaseDate

        //Click to Detail
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${data.id}", Toast.LENGTH_SHORT).show()
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("movie_id",data.id.toString())
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount()= listMovie.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ResultsItem>){
        listMovie.clear()
        listMovie.addAll(data)
        notifyDataSetChanged()
    }
}