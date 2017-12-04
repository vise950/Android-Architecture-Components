package com.eggon.androidd.androidarchitecturetest.application

import android.app.Application
import co.eggon.eggoid.ServiceFactory
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import retrofit2.converter.gson.GsonConverterFactory


class Init : Application() {

    private val dbName = "my_database"

    override fun onCreate() {
        super.onCreate()

        AppDatabase.buildDatabase(this, dbName)
        initServiceFactory()
    }

    private fun initServiceFactory() {
        ServiceFactory.init("https://api.darksky.net", true)
        ServiceFactory.converterFactory(GsonConverterFactory.create())
    }
}