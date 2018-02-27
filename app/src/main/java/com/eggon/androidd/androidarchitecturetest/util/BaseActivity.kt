package com.eggon.androidd.androidarchitecturetest.util

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {

    var disposables = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}