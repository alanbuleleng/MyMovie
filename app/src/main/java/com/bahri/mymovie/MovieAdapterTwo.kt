package com.bahri.mymovie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapterTwo(private var  listMovie: ArrayList<ResultsItem>):
    RecyclerView.Adapter<MovieAdapterTwo.myviewHolder>() {
    class myviewHolder (itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image : ImageView = itemview.findViewById(R.id.iv_image)
        val judul : TextView = itemview.findViewById(R.id.tv_judul_two)
        val date : TextView = itemview.findViewById(R.id.tv_date_two)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.upcoming_movie,parent,false)
        return myviewHolder(view)
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        val data = listMovie[position]
        Glide.with(holder.itemView)
            .load("https://www.themoviedb.org/t/p/w220_and_h330_face${data.posterPath}")
            .apply(RequestOptions.overrideOf(150,150)).into(holder.image)
        holder.judul.text = data.title
        holder.date.text = data.releaseDate

    }

    override fun getItemCount()= listMovie.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ResultsItem>){
        listMovie.clear()
        listMovie.addAll(data)
        notifyDataSetChanged()
    }
}