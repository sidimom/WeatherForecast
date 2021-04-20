package com.example.weatherforecast.modules

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.App
import com.example.weatherforecast.api.NetworkService
import com.example.weatherforecast.entities.CityItem
import com.example.weatherforecast.entities.WeatherItem
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val db = App.instance.getDatabase()

    val cities = MutableLiveData<List<CityItem>>()
    val weatherStates = MutableLiveData<List<WeatherItem>>()
    val error = MutableLiveData<String>()

    fun getCities() {
        viewModelScope.launch {
            val citiesData = db.wordDao().getAll()
            cities.value = citiesData
        }
    }

    fun addCity(cityItem: CityItem) {
        viewModelScope.launch {
            db.wordDao().addCity(cityItem)
            getCities()
        }
    }

    fun deleteCity(cityItem: CityItem) {
        viewModelScope.launch {
            db.wordDao().deleteCity(cityItem)
            getCities()
        }
    }

    fun getCityCoordinate(cityName: String) {
        viewModelScope.launch {
            try {
                val response = NetworkService.coordinateApi
                    .getCityCoordinate(cityName)
                if (response.isSuccessful) {
                    response.body()?.results?.getOrNull(0)?.geometry?.let {
                        getWeatherForecast(it.lat, it.lng)
                    }

                } else {
                    error.value = response.message()
                }
            } catch (e: Exception){
                error.value = e.toString()
            }
        }
    }

    private fun getWeatherForecast(lat: Double, lng: Double) {
        viewModelScope.launch {
            try {
                val response = NetworkService.weatherApi
                    .getWeatherForecast(lat, lng)
                if (response.isSuccessful) {
                    weatherStates.value = response.body()?.daily
                } else {
                    error.value = response.message()
                }
            } catch (e: Exception){
                error.value = e.toString()
            }
        }
    }
}