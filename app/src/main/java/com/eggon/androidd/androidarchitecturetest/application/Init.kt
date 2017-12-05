package com.eggon.androidd.androidarchitecturetest.application

import android.app.Application
import co.eggon.eggoid.ServiceFactory
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.database.DatabaseHolder
import retrofit2.converter.gson.GsonConverterFactory


class Init : Application() {

    private lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        DatabaseHolder().init(this)
        initServiceFactory()
    }

    private fun initServiceFactory() {
        ServiceFactory.init("https://api.darksky.net", true)
        ServiceFactory.converterFactory(GsonConverterFactory.create())
    }
}