package com.eggon.androidd.androidarchitecturetest.database

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class AppDatabase {

    companion object {
        fun buildDatabase(context: Context, name: String) {
            Realm.init(context)
            Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                    .name(name)
                    .deleteRealmIfMigrationNeeded()
                    .build())
        }

        fun getDatabase(): Realm = Realm.getDefaultInstance()
    }
}