package com.example.weatherforecast.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.databinding.WeatherRvItemBinding
import com.example.weatherforecast.entities.WeatherItem
import com.example.weatherforecast.utils.DateFormatter.formatLongToDay

class WeatherStateAdapter: RecyclerView.Adapter<WeatherStateAdapter.Holder>() {

    private var items = mutableListOf<WeatherItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder.init(parent)

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    fun setItems(items: List<WeatherItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class Holder(private val binding: WeatherRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherItem: WeatherItem) {
            binding.dateTv.text = formatLongToDay(weatherItem.dt)
            binding.weatherStateTv.text = weatherItem.weather.getOrNull(0)?.description
            binding.temperatureTv.text = weatherItem.temp.max.toString()
            binding.windSpeedTv.text = weatherItem.wind_speed.toString()
            binding.humidityTv.text = weatherItem.humidity.toString()
        }

        companion object{
            fun init(parent: ViewGroup) = Holder(WeatherRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    }
}