package com.eggon.androidd.androidarchitecturetest.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    (this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        this@hideKeyboard.currentFocus?.windowToken?.let {
            this.hideSoftInputFromWindow(it, 0)
        }
    }
}