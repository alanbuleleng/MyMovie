package com.bahri.mymovie.network.model

import com.google.gson.annotations.SerializedName

data class ResponseKecamatan(
    @SerializedName("kecamatan") val kecamatan :List<DataKecamatan>
)

class DataKecamatan (
    @SerializedName("id") val id_kec : Int,
    @SerializedName("id_kota") val id_kota : Int,
    @SerializedName("nama") val namakec : String
        )



