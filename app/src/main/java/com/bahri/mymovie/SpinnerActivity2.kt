package com.bahri.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bahri.mymovie.databinding.ActivitySpinner2Binding
import com.bahri.mymovie.network.ApiServisLokasi
import android.widget.AdapterView.OnItemSelectedListener
import com.bahri.mymovie.network.ResponseKota
import com.bahri.mymovie.network.model.ResponseKecamatan
import com.bahri.mymovie.network.model.ResponseKelurahan
import com.bahri.mymovie.network.model.ResponseProvinsi
import kotlinx.android.synthetic.main.activity_spinner2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpinnerActivity2 : AppCompatActivity() {
    private val binding: ActivitySpinner2Binding by lazy {
        ActivitySpinner2Binding.inflate(layoutInflater)
    }

    private val idProvinsi = ArrayList<String>()
    private val idKabupaten = ArrayList<String>()
    private val idkecamatan = ArrayList<String>()
    private val idkelurahan = ArrayList<String>()

    private val listProvinsi = ArrayList<String>()
    private val listKota = ArrayList<String>()
    private val listKecamatan = ArrayList<String>()
    private val listkelurahan = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fatchProvinsi()

        binding.spinProvinsi.onItemSelectedListener = object
            : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listKota.clear()
                fatchKabupaten(idProvinsi[p2])

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        binding.spinKota.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    listKecamatan.clear()
                    if (position != 0) {
                        fetchKecamatan(idKabupaten[position])
                    } else {
                        fetchKecamatan("")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        binding.spinKecamatan.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    listkelurahan.clear()
                    if (position != 0) {
                        fetchKelurahan(idkecamatan[position])

                    } else {
                        fetchKelurahan("")
                    }
                }


                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }


    }


    internal fun fetchKelurahan(idkecamatan: String){
        listkelurahan.add("---Pilih Kelurahan---")
        ApiServisLokasi.endnetwork.getKelurahan(idkecamatan).enqueue(object :Callback<ResponseKelurahan>{
                override fun onResponse(
                    call: Call<ResponseKelurahan>,
                    response: Response<ResponseKelurahan>
                ) {
                    if (response.isSuccessful){
                        response.body()?.kelurahan?.forEach { kel ->
                            idkelurahan.add((kel.id-1).toString())
                            listkelurahan.add(kel.nama )
                        }
                        val kelurahanspin = ArrayAdapter(
                            this@SpinnerActivity2,
                            androidx.constraintlayout.widget.R.layout
                                .support_simple_spinner_dropdown_item,
                            listkelurahan
                        )
                        kelurahanspin.setDropDownViewResource(
                            androidx.appcompat.R.layout
                                .support_simple_spinner_dropdown_item
                        )
                        binding.spinKelurahan.adapter = kelurahanspin
                    }
                }

                override fun onFailure(call: Call<ResponseKelurahan>, t: Throwable) {

                }
            })
    }
    internal fun fetchKecamatan(idKabupaten: String) {
        listKecamatan.add("---Pilih Kecamatan---")
        ApiServisLokasi.endnetwork.getKecamatan(idKabupaten)
            .enqueue(object : Callback<ResponseKecamatan> {
                override fun onResponse(
                    call: Call<ResponseKecamatan>,
                    response: Response<ResponseKecamatan>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.kecamatan?.forEach { kec ->
                            idkecamatan.add(
                                (kec.id_kec -1)
                                    .toString()
                            )
                            listKecamatan.add(kec.namakec)
                        }
                        val kecamatanspin = ArrayAdapter(
                            this@SpinnerActivity2,
                            androidx.constraintlayout.widget
                                .R.layout
                                .support_simple_spinner_dropdown_item,
                            listKecamatan
                        )
                        kecamatanspin
                            .setDropDownViewResource(
                                androidx.appcompat
                                    .R.layout
                                    .support_simple_spinner_dropdown_item
                            )
                        binding.spinKecamatan.adapter = kecamatanspin
                    }

                }

                override fun onFailure(call: Call<ResponseKecamatan>, t: Throwable) {
                }
            })
    }

    internal fun fatchKabupaten(idProvinsi: String) {
        listKota.add("---Pilih Kabupaten---")
        ApiServisLokasi.endnetwork.getKota(idProvinsi)
            .enqueue(object : Callback<ResponseKota> {
                override fun onResponse(
                    call: Call<ResponseKota>,
                    response: Response<ResponseKota>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.kota?.forEach { Kab ->
                            idKabupaten.add(
                                (Kab.id - 1)
                                    .toString()
                            )
                            listKota.add(Kab.nama)
                        }
                        val kabupatenSpin = ArrayAdapter(
                            this@SpinnerActivity2,
                            androidx.constraintlayout.widget
                                .R.layout
                                .support_simple_spinner_dropdown_item,
                            listKota
                        )
                        kabupatenSpin
                            .setDropDownViewResource(
                                androidx.appcompat
                                    .R.layout
                                    .support_simple_spinner_dropdown_item
                            )
                        binding.spinKota.adapter = kabupatenSpin
                    }
                }

                override fun onFailure(call: Call<ResponseKota>, t: Throwable) {
                }
            })
    }

    private fun fatchProvinsi() {
        listProvinsi.add("--- Pilih Provinsi ---")
        ApiServisLokasi.endnetwork.getProvinsi().enqueue(object : Callback<ResponseProvinsi> {
            override fun onResponse(
                call: Call<ResponseProvinsi>,
                response: Response<ResponseProvinsi>
            ) {
                if (response.isSuccessful) {
                    response.body()?.provinsi?.forEach { prov ->
                        idProvinsi.add((prov.idprovinsi - 1).toString())
                        listProvinsi.add(prov.nama)
                    }
                    val provinsiSpin = ArrayAdapter(
                        this@SpinnerActivity2,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        listProvinsi
                    )
                    provinsiSpin.setDropDownViewResource(
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
                    )

                    binding.spinProvinsi.adapter = provinsiSpin
                }
            }

            override fun onFailure(call: Call<ResponseProvinsi>, t: Throwable) {
            }

        })
    }

}
