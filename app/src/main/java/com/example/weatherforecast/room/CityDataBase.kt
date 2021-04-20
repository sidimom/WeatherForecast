package com.example.weatherforecast.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherforecast.entities.CityItem

@Database(entities = [CityItem::class], version = 1)
abstract class CityDataBase: RoomDatabase() {
    abstract fun wordDao(): CityDao
    companion object {
        private var database: CityDataBase? = null
        fun getDataBase(context: Context): CityDataBase {
            return if (database == null)
                Room.databaseBuilder(context, CityDataBase::class.java, "database").build()
            else
                database!!
        }
    }
}