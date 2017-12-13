package com.eggon.androidd.androidarchitecturetest.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.eggon.androidd.androidarchitecturetest.model.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather")
    fun getData(): LiveData<Weather>

    @Query("SELECT COUNT(*) FROM Weather")
    fun count(): Int

    @Insert()
    fun insertData(w: Weather)

    @Update(onConflict = REPLACE)
    fun updateData(w: Weather)

    @Delete
    fun removeData(w: Weather)
}