package com.eggon.androidd.androidarchitecturetest.application

import android.app.Application
import co.eggon.eggoid.ServiceFactory
import retrofit2.converter.gson.GsonConverterFactory


class Init : Application() {

    override fun onCreate() {
        super.onCreate()

        initServiceFactory()
    }

    private fun initServiceFactory() {
        ServiceFactory.init("https://api.darksky.net", true)
        ServiceFactory.addConverterFactory(GsonConverterFactory.create())
    }
}