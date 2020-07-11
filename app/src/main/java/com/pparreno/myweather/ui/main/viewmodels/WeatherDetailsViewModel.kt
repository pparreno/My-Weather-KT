package com.pparreno.myweather.ui.main.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.pparreno.myweather.R
import com.pparreno.myweather.repository.Repository
import kotlinx.coroutines.Dispatchers

class WeatherDetailsViewModel(application: Application) : AndroidViewModel(application) {

    var repository : Repository = Repository
    lateinit var cityName : String
    val cityWeather = liveData(Dispatchers.IO) {
        val retrievedCityWeather = repository.getCityWeather(getApplication<Application>().resources.getString(
            R.string.api_key), cityName)
        emit(retrievedCityWeather)
    }
}