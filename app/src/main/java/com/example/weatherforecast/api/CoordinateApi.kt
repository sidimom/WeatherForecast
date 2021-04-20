package com.example.weatherforecast.api

import com.example.weatherforecast.entities.Coordinate
import com.example.weatherforecast.utils.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoordinateApi {
    @GET("/geocode/v1/json")
    suspend fun getCityCoordinate(@Query("q") cityName: String,
                          @Query("key") apiKey: String = Const.COORDINATE_API_KEY
    ): Response<Coordinate>
}