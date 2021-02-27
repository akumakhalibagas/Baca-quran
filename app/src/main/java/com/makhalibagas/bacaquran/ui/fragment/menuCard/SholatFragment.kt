package com.makhalibagas.bacaquran.ui.fragment.menuCard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.makhalibagas.bacaquran.R
import com.makhalibagas.bacaquran.model.sholat.ResponseSholat
import com.makhalibagas.bacaquran.retrofit.ApiService
import com.makhalibagas.bacaquran.retrofit.RetroConfig
import kotlinx.android.synthetic.main.fragment_sholat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class SholatFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sholat, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvCity.text = "Jawa Tengah"
        getData("Jawatengah")
        val searchView: SearchView = view.findViewById(R.id.sv)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getData(query)
                tvCity.text = query
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getData(query: String) {
        RetroConfig.getRetrofitSholat().create(ApiService::class.java).getJadwalSholat(query, "Indonesia").enqueue(object : Callback<ResponseSholat?> {
            override fun onResponse(call: Call<ResponseSholat?>, response: Response<ResponseSholat?>) {
                if (response.body() != null) {
                    tvSubuh.text = response.body()!!.data.timings.fajr
                    tvDhuhur.text = response.body()!!.data.timings.dhuhr
                    tvAshar.text = response.body()!!.data.timings.asr
                    tvMagrib.text = response.body()!!.data.timings.maghrib
                    tvIsya.text = response.body()!!.data.timings.isha
                }
            }

            override fun onFailure(call: Call<ResponseSholat?>, t: Throwable) {}
        })
    }
}