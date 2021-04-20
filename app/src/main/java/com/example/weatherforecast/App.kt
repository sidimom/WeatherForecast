package com.example.weatherforecast

import android.app.Application
import androidx.room.Room
import com.example.weatherforecast.room.CityDataBase


class App: Application() {
    private lateinit var database: CityDataBase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, CityDataBase::class.java, "database").build()
    }

    fun getDatabase() = database

    companion object {
        lateinit var instance: App
    }
}