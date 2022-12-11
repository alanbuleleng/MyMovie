package com.bahri.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bahri.mymovie.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.trending_movie.view.*
import kotlinx.android.synthetic.main.upcoming_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val movieActivity = MovieAdapter(arrayListOf())
    val movieAdapterScroll = MovieAdapterTwo(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_um.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_um.adapter = movieAdapterScroll

        rv_tm.layoutManager = LinearLayoutManager(this)
        rv_tm.adapter = movieActivity
        getMovie()

    }

    private fun getMovie() {
        ApiService.endnetwork.getMovie().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    val responseMovie: MovieResponse? = response.body()
                    responseMovie?.let { onResultMovie(it) }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

        })
    }

    private fun onResultMovie(responseMovie: MovieResponse) {
        val result = responseMovie.results
        val resultrr = responseMovie.results
        movieActivity.setData(result as List<ResultsItem>)
        movieAdapterScroll.setData(resultrr as List<ResultsItem>)
    }
}