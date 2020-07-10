package com.pparreno.myweather.retrofit

import com.pparreno.myweather.models.CityWeather
import com.pparreno.myweather.models.GroupWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getCityWeather(@Query("appid") apiKey: String, @Query("q") cityName: String) : CityWeather

    @GET("group?units=metric")
    suspend fun getGroupWeather(@Query("appid") apiKey: String, @Query("id") cityIDs: String): GroupWeatherResponse
}