package com.eggon.androidd.androidarchitecturetest.dagger

import com.eggon.androidd.androidarchitecturetest.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}