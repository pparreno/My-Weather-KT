package com.pparreno.myweather.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pparreno.myweather.R
import com.pparreno.myweather.ui.main.viewmodels.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private val TAG: String = "MAINFRAGMENT"
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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