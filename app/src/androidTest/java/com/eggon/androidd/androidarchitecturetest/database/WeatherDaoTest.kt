package com.eggon.androidd.androidarchitecturetest.database

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.eggon.androidd.androidarchitecturetest.LiveDataTestUtil
import com.eggon.androidd.androidarchitecturetest.database.TestData.Companion.WEATHER_ENTITY
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: WeatherDao

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        dao = db.weatherDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun getDataWhenNoDataInserted() {
        LiveDataTestUtil.getValue(dao.getData())
    }

    @Test
    @Throws(InterruptedException::class)
    fun getProductsAfterInserted() {
        dao.insertData(WEATHER_ENTITY)
        LiveDataTestUtil.getValue(dao.getData())
    }
}