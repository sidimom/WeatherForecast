package com.example.weatherforecast.ui

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.entities.CityItem
import com.example.weatherforecast.modules.MainViewModel
import com.example.weatherforecast.ui.adapters.CityAdapter
import com.example.weatherforecast.ui.adapters.WeatherStateAdapter


class MainActivity : AppCompatActivity() {

    private var bindingNull: ActivityMainBinding? = null
    private val binding
        get() = bindingNull!!
    private val cityAdapter = CityAdapter()
    private val weatherStateAdapter = WeatherStateAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNull = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.burgerIv.setOnClickListener { openDrawer() }
        binding.addBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.add_city_title)
            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)
            builder.setPositiveButton(R.string.Ok) { _, _ ->
                val cityName = input.text.toString()
                if (cityName.isNotEmpty())
                    viewModel.addCity(CityItem(cityName))
            }
            builder.show()
        }
        binding.citiesRv.adapter = cityAdapter
        cityAdapter.listener = { cityItem ->
            closeDrawer()
            setCityWeather(cityItem)
        }
        binding.weatherStateRv.adapter = weatherStateAdapter

        viewModel.cities.observe(this) { cities ->
            cityAdapter.setItems(cities)
            cities.getOrNull(0)?.let { setCityWeather(it) }
        }
        viewModel.weatherStates.observe(this) { items ->
            weatherStateAdapter.setItems(items)
        }
        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
        viewModel.getCities()
    }

    private fun setCityWeather(cityItem: CityItem) {
        binding.cityWeatherTv.text = getString(R.string.city_weather_text, cityItem.name)
        viewModel.getCityCoordinate(cityItem.name)
    }

    private fun openDrawer() {
        if (!binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun closeDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onDestroy() {
        bindingNull = null
        super.onDestroy()
    }
}