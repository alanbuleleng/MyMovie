package com.bahri.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bahri.mymovie.network.ApiService
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.trending_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val b = intent
        val id = b.getStringExtra("movie_id")
        getDetailMovie(id)
    }

    private fun getDetailMovie(id: String?) {
        ApiService.endnetwork.getDetail(id!!).enqueue(
            object : Callback<DetailMovieResponse>{
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful){
                    val detail: DetailMovieResponse? = response.body()
                    onResultDetail(detail)
                }else{
                    Toast.makeText(this@DetailActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "$t", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun onResultDetail(detail: DetailMovieResponse?) {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/${detail!!.backdrop_path}")
            .into(iv_detail)
        tv_detail_Judul.text = detail.title
        tv_detail_gendre.text = detail.adult.toString()
        tv_detail_popularity.text = detail.popularity.toString()
        tv_detail_overview.text = detail.overview

    }
}