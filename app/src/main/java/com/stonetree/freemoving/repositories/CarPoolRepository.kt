package com.stonetree.freemoving.repositories

import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.restclient.feature.repository.CoreRepository

interface CarPoolRepository {

    fun get(): CoreRepository

    fun load(callback: List<Car>.() -> Unit)
}