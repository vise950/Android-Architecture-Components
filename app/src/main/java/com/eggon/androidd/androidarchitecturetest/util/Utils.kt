package com.eggon.androidd.androidarchitecturetest.util

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

class Utils {

    companion object {

        fun makeSnackbar(context: Context, layout: View): Snackbar =
                Snackbar.make(layout, "Loading...", Snackbar.LENGTH_INDEFINITE).apply {
                    (view.findViewById<View>(android.support.design.R.id.snackbar_text).parent as ViewGroup)
                            .addView(ProgressBar(context))
                }
    }
}