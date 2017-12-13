package com.eggon.androidd.androidarchitecturetest.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import javax.inject.Inject


class ViewModelFactory @Inject constructor(private val repo: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = WeatherViewModel(repo) as T
}