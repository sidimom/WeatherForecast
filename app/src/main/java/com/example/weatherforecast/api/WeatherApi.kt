package com.example.weatherforecast.api

import com.example.weatherforecast.entities.WeatherItems
import com.example.weatherforecast.utils.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/onecall")
    suspend fun getWeatherForecast(@Query("lat") lat: Double,
                           @Query("lon") lon: Double,
                           @Query("exclude") exclude: String = "current,minutely,hourly,alerts",
                           @Query("appid") apiKey: String = Const.WEATHER_FORECAST_API_KEY
    ) : Response<WeatherItems>
}