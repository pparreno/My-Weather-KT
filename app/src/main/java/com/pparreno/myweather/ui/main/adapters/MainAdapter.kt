package com.pparreno.myweather.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.pparreno.myweather.R
import com.pparreno.myweather.models.CityWeather

class MainAdapter(var data: LiveData<List<CityWeather>>) : RecyclerView.Adapter<MainItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_city_weather_item, parent, false) as ViewGroup

        return MainItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (this.data.value != null) this.data.value!!.size else 0
    }

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {

        val model = this.data.value?.get(position)

        holder.tempText.text = model!!.main.temperature.toString()
        holder.cityNameText.text = model.name;
        holder.weatherStatusText.text = model.weather[0].main

    }
}

class MainItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val favoriteIcon : ImageView = itemView.findViewById(R.id.favorite_icon)
    val weatherIcon : ImageView = itemView.findViewById(R.id.weather_icon)
    val tempText : TextView = itemView.findViewById(R.id.temp_text)
    val cityNameText : TextView = itemView.findViewById(R.id.city_text)
    val weatherStatusText : TextView = itemView.findViewById(R.id.weather_text)


}
