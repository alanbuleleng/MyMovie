package com.bahri.mymovie

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("title") val title: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("overview") val overview: String

)
