package com.pparreno.myweather.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pparreno.myweather.R
import com.pparreno.myweather.ui.main.viewmodels.WeatherDetailsViewModel
import com.pparreno.myweather.utils.SharedPrefUtils
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var cityName: String? = null

    private lateinit var viewModel: WeatherDetailsViewModel

    private lateinit var cityNameText : TextView
    private lateinit var temperatureText : TextView
    private lateinit var tempRangeText : TextView
    private lateinit var weatherText : TextView
    private lateinit var favoriteIcon : ImageView

    private var isFavorite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityName = it.getString(ARG_PARAM1)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather_details, container, false)
        cityNameText = view.findViewById(R.id.city_text)
        temperatureText = view.findViewById(R.id.temp_text)
        tempRangeText = view.findViewById(R.id.temp_range_text)
        weatherText = view.findViewById(R.id.weather_text)
        favoriteIcon = view.findViewById(R.id.favorite_icon)

        favoriteIcon.setOnClickListener(View.OnClickListener {
            if(isFavorite) {
                if(SharedPrefUtils.removeCityNameAsFavorite(this.cityName!!, context!!))
                {
                    isFavorite = false;
                }
            } else {
                if(SharedPrefUtils.setCityNameAsFavorite(this.cityName!!, context!!))
                {
                    isFavorite = true;
                }
            }
            toggleFavoriteIconImageState()
        })

        isFavorite = SharedPrefUtils.isCityNameFavorite(cityName!!, context!!)
        toggleFavoriteIconImageState()

        return view;
    }

    private fun toggleFavoriteIconImageState() {
        if(isFavorite) {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_favorite)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(WeatherDetailsViewModel::class.java)
        viewModel.cityName = cityName!!
        viewModel.cityWeather.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            cityNameText.text = it.name
            cityName = it.name
            temperatureText.text = String.format(Locale.getDefault(), "%.1f°C", it.main.temperature)
            weatherText.text = it.weather[0].main
            tempRangeText.text = String.format(Locale.getDefault(), "High %d°C / Low %d°C", it.main.tempMax.toInt(), it.main.tempMin.toInt())

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeatherDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2 : Boolean) =
            WeatherDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putBoolean(ARG_PARAM2, param2)
                }
            }
    }
}