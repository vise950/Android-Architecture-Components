package com.eggon.androidd.androidarchitecturetest.application

import android.app.Application
import co.eggon.eggoid.ServiceFactory
import com.eggon.androidd.androidarchitecturetest.dagger.component.AppComponent
import com.eggon.androidd.androidarchitecturetest.dagger.component.DaggerAppComponent
import com.eggon.androidd.androidarchitecturetest.dagger.module.AppModule
import retrofit2.converter.gson.GsonConverterFactory


class Init : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initDagger()
        initServiceFactory()
    }

    private fun initServiceFactory() {
        ServiceFactory.init("https://api.darksky.net", true)
        ServiceFactory.addConverterFactory(GsonConverterFactory.create())
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}