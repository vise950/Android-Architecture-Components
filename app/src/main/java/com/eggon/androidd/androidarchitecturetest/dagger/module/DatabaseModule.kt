package com.eggon.androidd.androidarchitecturetest.dagger.module

import android.arch.persistence.room.Room
import android.content.Context
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import com.eggon.androidd.androidarchitecturetest.repository.local.WeatherLocalRepository
import com.eggon.androidd.androidarchitecturetest.repository.remote.WeatherRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "my_database").build()

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): WeatherDao = db.weatherDao()

    @Provides
    fun provideLocalRepo(dao: WeatherDao): WeatherLocalRepository = WeatherLocalRepository(dao)

    @Provides
    fun provideRemoteRepo(dao: WeatherDao): WeatherRemoteRepository = WeatherRemoteRepository(dao)

    @Provides
    fun provideRepo(context: Context, localRepository: WeatherLocalRepository, remoteRepository: WeatherRemoteRepository): WeatherRepository =
            WeatherRepository(context, localRepository, remoteRepository)
}