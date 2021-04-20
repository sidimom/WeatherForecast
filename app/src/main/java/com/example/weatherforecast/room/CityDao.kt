package com.example.weatherforecast.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherforecast.entities.CityItem

@Dao
interface CityDao {
    @Query("SELECT * FROM cityitem")
    suspend fun getAll(): List<CityItem>

    @Insert
    suspend fun addCity(cityItem: CityItem)

    @Delete
    suspend fun deleteCity(cityItem: CityItem)
}