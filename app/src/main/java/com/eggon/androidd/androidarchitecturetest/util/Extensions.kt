package com.eggon.androidd.androidarchitecturetest.util

import io.realm.RealmModel
import io.realm.RealmResults


// Realm extension
fun <T : RealmModel> RealmResults<T>.asLiveData() = RealmLiveData(this)