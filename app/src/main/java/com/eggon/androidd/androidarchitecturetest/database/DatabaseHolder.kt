package com.eggon.androidd.androidarchitecturetest.database

import android.arch.persistence.room.Room
import android.content.Context
import android.support.annotation.MainThread


class DatabaseHolder {

    private val dbName = "my_database"
    private var database: AppDatabase? = null

    @MainThread
    fun init(context: Context) {
        database = Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()
    }

    fun database(): AppDatabase? = database
}