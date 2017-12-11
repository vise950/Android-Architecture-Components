package com.eggon.androidd.androidarchitecturetest.application

import android.app.Application
import co.eggon.eggoid.ServiceFactory
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import retrofit2.converter.gson.GsonConverterFactory


class Init : Application() {

    override fun onCreate() {
        super.onCreate()

        AppDatabase.createDatabase(this)
        initServiceFactory()
    }

    override fun onTerminate() {
        super.onTerminate()
        AppDatabase.destroyDatabase()
    }

    private fun initServiceFactory() {
        ServiceFactory.init("https://api.darksky.net", true)
        ServiceFactory.converterFactory(GsonConverterFactory.create())
    }
}