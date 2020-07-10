package com.pparreno.myweather.models

import com.google.gson.annotations.SerializedName

data class GroupWeatherResponse (
    @SerializedName("cnt")
    val count: Int,
    @SerializedName("list")
    val data: List<CityWeather>
)
