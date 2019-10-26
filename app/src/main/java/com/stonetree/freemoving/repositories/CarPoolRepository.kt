package com.stonetree.freemoving.repositories

import androidx.lifecycle.MutableLiveData
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.feature.pool.model.CarPool
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.feature.repository.CoreRepository

interface CarPoolRepository {

    fun get(): CoreRepository

    fun load(callback: List<Car>.() -> Unit)
}