package com.eggon.androidd.androidarchitecturetest.dagger.component

import com.eggon.androidd.androidarchitecturetest.dagger.module.AppModule
import com.eggon.androidd.androidarchitecturetest.dagger.module.DatabaseModule
import com.eggon.androidd.androidarchitecturetest.dagger.module.RepositoryModule
import com.eggon.androidd.androidarchitecturetest.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}