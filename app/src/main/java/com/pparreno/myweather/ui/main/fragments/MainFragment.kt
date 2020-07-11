package com.pparreno.myweather.ui.main.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pparreno.myweather.R
import com.pparreno.myweather.models.CityWeather
import com.pparreno.myweather.ui.main.adapters.MainAdapter
import com.pparreno.myweather.ui.main.adapters.OnItemClickListener
import com.pparreno.myweather.ui.main.viewmodels.MainViewModel
import timber.log.Timber


class MainFragment : Fragment(), OnItemClickListener {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MainAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val TAG: String = "MAINFRAGMENT"
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        viewManager = LinearLayoutManager(context)
        swipeRefreshLayout = view.findViewById(R.id.main)
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
        viewAdapter = MainAdapter(viewModel.groupWeather, this)
        recyclerView.adapter = viewAdapter
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            viewModel.refresh()
        })

    }

    override fun onStart() {
        super.onStart()
        viewModel.groupWeather.observe(viewLifecycleOwner, Observer {
            Timber.d("length of result: %s", it.size)
            swipeRefreshLayout.isRefreshing = false
            viewAdapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(item: CityWeather?) {
        replaceFragment(item!!.name, false)
    }

    private fun replaceFragment(cityName : String, isFavorite : Boolean) {
        val newFragment: Fragment = WeatherDetailsFragment.newInstance(cityName, isFavorite)
        val transaction: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}