package com.eggon.androidd.androidarchitecturetest.application

import android.app.Application
import co.eggon.eggoid.ServiceFactory
import com.eggon.androidd.androidarchitecturetest.dagger.AppComponent
import com.eggon.androidd.androidarchitecturetest.dagger.AppModule
import com.eggon.androidd.androidarchitecturetest.dagger.DaggerAppComponent
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import retrofit2.converter.gson.GsonConverterFactory


class Init : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        AppDatabase.createDatabase(this)
        initServiceFactory()
    }

    override fun onTerminate() {
        super.onTerminate()
        AppDatabase.closeDatabase()
    }

    private fun initServiceFactory() {
        ServiceFactory.init("https://api.darksky.net", true)
        ServiceFactory.addConverterFactory(GsonConverterFactory.create())
    }
}