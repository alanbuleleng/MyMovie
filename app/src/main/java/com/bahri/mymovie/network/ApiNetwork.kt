package com.bahri.mymovie.network

import com.bahri.mymovie.DetailMovieResponse
import com.bahri.mymovie.MovieResponse
import com.bahri.mymovie.network.model.ResponseKecamatan
import com.bahri.mymovie.network.model.ResponseKelurahan
import com.bahri.mymovie.network.model.ResponseProvinsi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiNetwork {
    @GET("movie/popular?api_key=bc79104b108ca2dee02339203c934fd1&language=en-US&page=1")
    fun getMovie(): Call<MovieResponse>

    @GET("movie/{movie_id}}?api_key=bc79104b108ca2dee02339203c934fd1&language=en-US")
    fun getDetail(
        @Path("movie_id") movie_id: String
    ): Call<DetailMovieResponse>

    @GET("provinsi")
    fun getProvinsi(): Call<ResponseProvinsi>

    @GET("kota")
    fun getKota(
        @Query("id_provinsi") id_provinsi:String
    ):Call<ResponseKota>

    @GET("kecamatan")
    fun getKecamatan(
        @Query("id_kota") id_kota: String
    ):Call<ResponseKecamatan>

    @GET("kelurahan")
    fun getKelurahan(
        @Query("id_kecamatan") id_kecamatan:String
    ):Call<ResponseKelurahan>


}