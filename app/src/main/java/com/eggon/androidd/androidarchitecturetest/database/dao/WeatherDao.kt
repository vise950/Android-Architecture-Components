package com.eggon.androidd.androidarchitecturetest.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.eggon.androidd.androidarchitecturetest.model.DailyData
import com.eggon.androidd.androidarchitecturetest.model.Weather

const val WEATHER_QUERY = """
SELECT * FROM weather
WHERE latitude = :lat
AND longitude = :lng
ORDER BY timestamp
LIMIT 1
"""

const val DAILY_DATA_QUERY = """
SELECT * FROM daily_data
WHERE latitude = :lat
AND longitude = :lng
AND timestamp = :timestamp
"""

@Dao
interface WeatherDao {
    @Query(WEATHER_QUERY)
    fun getData(lat: Double, lng: Double): LiveData<Weather>

    @Query(DAILY_DATA_QUERY)
    fun getDailyData(lat: Double, lng: Double, timestamp: Long): LiveData<List<DailyData>>

    @Query("SELECT COUNT(*) FROM weather")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(w: Weather)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateData(w: Weather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyData(vararg w: DailyData)

    @Delete
    fun removeData(w: Weather)
}