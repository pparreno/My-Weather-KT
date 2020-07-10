package com.pparreno.myweather.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pparreno.myweather.R
import com.pparreno.myweather.ui.main.adapters.MainAdapter
import com.pparreno.myweather.ui.main.viewmodels.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MainAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val TAG: String = "MAINFRAGMENT"
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var view = inflater.inflate(R.layout.main_fragment, container, false)
        viewManager = LinearLayoutManager(context)
        recyclerView = view.findViewById<RecyclerView>(R.id.main_recyclerview).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

        }
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewAdapter = MainAdapter(viewModel.groupWeather)
        recyclerView.adapter = viewAdapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.groupWeather.observe(this, Observer {
            Log.d(TAG, "length of result: " + it.size)
            viewAdapter.notifyDataSetChanged()
        })





    }
}