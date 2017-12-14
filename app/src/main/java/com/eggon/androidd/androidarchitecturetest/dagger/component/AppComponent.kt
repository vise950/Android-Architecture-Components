package com.eggon.androidd.androidarchitecturetest.dagger.component

import com.eggon.androidd.androidarchitecturetest.ui.MainActivity
import com.eggon.androidd.androidarchitecturetest.dagger.module.AppModule
import com.eggon.androidd.androidarchitecturetest.dagger.module.DatabaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}