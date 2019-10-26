package com.stonetree.freemoving.repositories

import androidx.lifecycle.MutableLiveData
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.feature.pool.model.CarPool
import com.stonetree.restclient.core.model.NetworkState

interface CarPoolRepository {

    fun network(): MutableLiveData<NetworkState>

    fun load(callback: List<Car>.() -> Unit)
}