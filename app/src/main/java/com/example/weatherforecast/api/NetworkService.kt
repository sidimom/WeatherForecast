package com.example.weatherforecast.api

import com.example.weatherforecast.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object NetworkService {
    var weatherApi: WeatherApi
    var coordinateApi: CoordinateApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.WEATHER_FORECAST_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)

        val coordinateRetrofit = Retrofit.Builder()
            .baseUrl(Const.COORDINATE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        coordinateApi = coordinateRetrofit.create(CoordinateApi::class.java)
    }
}