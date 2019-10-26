package com.stonetree.freemoving.feature.pool.viewmodel

import androidx.lifecycle.ViewModel
import com.stonetree.freemoving.repositories.CarPoolRepository
import com.stonetree.freemoving.sources.factories.CarPoolSourceFactory

class CarPoolViewModel(
    val repository: CarPoolRepository,
    val factory: CarPoolSourceFactory
) : ViewModel() {

    fun retry() {

    }
}