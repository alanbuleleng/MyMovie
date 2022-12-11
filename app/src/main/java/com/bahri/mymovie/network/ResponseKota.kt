package com.bahri.mymovie.network

import com.google.gson.annotations.SerializedName

data class ResponseKota(
    @SerializedName ("kota_kabupaten") val kota: List<DataKota>

)
data class DataKota(
    @SerializedName ("id") val id: Int,
    @SerializedName ("id_provinsi") val id_provinsi: Int,
    @SerializedName ("nama") val nama: String,
)

