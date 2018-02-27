package com.eggon.androidd.androidarchitecturetest.dagger.module

import android.content.Context
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import com.eggon.androidd.androidarchitecturetest.repository.local.WeatherLocalRepository
import com.eggon.androidd.androidarchitecturetest.repository.remote.WeatherRemoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideLocalRepo(dao: WeatherDao): WeatherLocalRepository = WeatherLocalRepository(dao)

    @Provides
    fun provideRemoteRepo(dao: WeatherDao): WeatherRemoteRepository = WeatherRemoteRepository(dao)

    @Provides
    fun provideRepo(context: Context, localRepository: WeatherLocalRepository, remoteRepository: WeatherRemoteRepository): WeatherRepository =
            WeatherRepository(context, localRepository, remoteRepository)
}