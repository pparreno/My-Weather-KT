package com.pparreno.myweather.models

import com.google.gson.annotations.SerializedName

data class CityWeather(
    @SerializedName("coord")
    val coordinates: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: MainStats,
    val visibility: String,
    @SerializedName("wind")
    val windStats: WindStats,
    @SerializedName("clouds")
    val cloudsStats : CloudsStats,
    val id: Int,
    val name: String,
    val cod: Int
)

class CloudsStats (
    val all : Int
)

data class WindStats (
    val speed : Float,
    val deg : Int
)

data class MainStats (
    @SerializedName("temp")
    val temperature : Float,
    val pressure : Int,
    val humidity : Int,
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("temp_max")
    val tempMax: Float
)

data class Coordinates (
    val lon: Float,
    val lat: Float
)

data class Weather (
    val id : Int,
    val main: String,
    val description: String,
    val icon: String
) {
    fun iconURLString(): String {
        return String.format("https://openweathermap.org/img/wn/%s@2x.png", icon)
    }
}
