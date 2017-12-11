package com.eggon.androidd.androidarchitecturetest.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.eggon.androidd.androidarchitecturetest.model.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather LIMIT 1")
    fun getData(): LiveData<Weather>

    @Query("SELECT COUNT(*) FROM Weather")
    fun count(): Int

    @Insert()
    fun insertData(w: Weather)

    @Delete
    fun removeData(w: Weather)
}