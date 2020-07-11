package com.pparreno.myweather.ui.main.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.pparreno.myweather.R
import com.pparreno.myweather.models.CityWeather
import com.pparreno.myweather.models.GroupWeatherResponse
import com.pparreno.myweather.repository.Repository
import com.pparreno.myweather.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var repository : Repository = Repository()

    val cityWeather = liveData(Dispatchers.IO) {
        val retrievedCityWeather = repository.getCityWeather(getApplication<Application>().resources.getString(
            R.string.api_key), "Manila")
        emit(retrievedCityWeather)
    }

    var groupWeather : MutableLiveData<List<CityWeather>> = liveData(Dispatchers.IO){
        Timber.d("Starting fetching of group weather")
        val retrievedCityWeather = callGroupWeatherService()
        emit(retrievedCityWeather.data)
    } as MutableLiveData<List<CityWeather>>

    fun refresh() {
        val scope =  CoroutineScope(Dispatchers.IO)
       scope.launch {
           val retrievedCityWeather = callGroupWeatherService()
           groupWeather.postValue(retrievedCityWeather.data)
       }
    }

    private suspend fun callGroupWeatherService(): GroupWeatherResponse {
        val cityIDs = Utils.getPredefinedCityIDs();
        return repository.getGroupWeather(
            getApplication<Application>().resources.getString(
                R.string.api_keyd
            ), Utils.buildGroupCityQueryParam(cityIDs)
        )
    }


}