package com.eggon.androidd.androidarchitecturetest.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.eggon.androidd.androidarchitecturetest.model.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather")
    fun getData(): LiveData<Weather>

    @Query("SELECT COUNT(*) FROM Weather")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(w: Weather)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateData(w: Weather)

    @Delete
    fun removeData(w: Weather)
}