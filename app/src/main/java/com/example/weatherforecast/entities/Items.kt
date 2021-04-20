package com.example.weatherforecast.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CityItem(@PrimaryKey val name: String)

class WeatherItem(val dt: Long,
                  val weather: List<WeatherState>,
                  val temp: Temperature,
                  val wind_speed: Double,
                  val humidity: Int)

class WeatherState(val main: String,
                   val description: String)

class Temperature(val max: Double)

class Coordinate(val results: List<CoordinateResult>)

class CoordinateResult(val geometry: CoordinateGeometry)

class CoordinateGeometry(val lat: Double,
                         val lng: Double)

class WeatherItems(val daily: List<WeatherItem>)