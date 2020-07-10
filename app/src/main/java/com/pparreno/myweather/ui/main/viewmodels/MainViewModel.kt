package com.pparreno.myweather.ui.main.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.pparreno.myweather.R
import com.pparreno.myweather.repository.Repository
import com.pparreno.myweather.utils.Utils
import kotlinx.coroutines.Dispatchers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var repository : Repository = Repository()

    val cityWeather = liveData(Dispatchers.IO) {
        val retrievedCityWeather = repository.getCityWeather(getApplication<Application>().resources.getString(
            R.string.api_key), "Manila")

        emit(retrievedCityWeather)
    }

    val groupWeather = liveData(Dispatchers.IO){
        val cityIDs = Utils.getPredefinedCityIDs();
        val retrievedCityWeather = repository.getGroupWeather(getApplication<Application>().resources.getString(
            R.string.api_key), Utils.buildGroupCityQueryParam(cityIDs))
        emit(retrievedCityWeather)
    }

}