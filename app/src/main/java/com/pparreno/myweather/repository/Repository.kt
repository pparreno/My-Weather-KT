package com.pparreno.myweather.repository

import com.pparreno.myweather.retrofit.ServiceBuilder
import com.pparreno.myweather.retrofit.WeatherService

object  Repository {

    var webService = ServiceBuilder.buildService(WeatherService::class.java)

    suspend fun getCityWeather(apiKey: String, cityName: String) =
            webService.getCityWeather(apiKey, cityName)

    suspend fun getGroupWeather(apiKey: String, citiesParam: String) =
        webService.getGroupWeather(apiKey, citiesParam)
}