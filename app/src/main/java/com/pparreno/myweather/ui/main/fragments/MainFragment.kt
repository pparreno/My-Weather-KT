package com.pparreno.myweather.ui.main.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pparreno.myweather.R
import com.pparreno.myweather.models.CityWeather
import com.pparreno.myweather.retrofit.WeatherService
import com.pparreno.myweather.retrofit.ServiceBuilder
import com.pparreno.myweather.ui.main.viewmodels.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private val TAG: String = "MAINFRAGMENT"
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val request = ServiceBuilder.buildService(WeatherService::class.java)
//        val call = request.getCityWeather(getString(R.string.api_key),"Manila")
//
//        call.enqueue(object : Callback<CityWeather> {
//            override fun onFailure(call: Call<CityWeather>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<CityWeather>, response: Response<CityWeather>) {
//                Log.d(TAG, response.toString())
//            }
//
//        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.groupWeather.observe(this, Observer {
            Log.d(TAG, "length of result: " + it.count)
        })

    }
}