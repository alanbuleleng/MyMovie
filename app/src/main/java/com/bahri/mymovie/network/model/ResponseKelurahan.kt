package com.bahri.mymovie.network.model

import com.google.gson.annotations.SerializedName

class ResponseKelurahan (
    @SerializedName ("kelurahan") val kelurahan:List<DaftarKelurahan>
        )

class DaftarKelurahan (
    @SerializedName("id") val id: Int,
    @SerializedName("id_kecamatan") val id_kecamatan:Int,
    @SerializedName("nama") val nama: String
        )
