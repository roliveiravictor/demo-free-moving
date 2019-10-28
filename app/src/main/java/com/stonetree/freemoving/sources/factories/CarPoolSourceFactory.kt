package com.stonetree.freemoving.sources.factories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.repositories.CarPoolRepository
import com.stonetree.freemoving.sources.CarPoolDataSource

class CarPoolSourceFactory(
    private val repository: CarPoolRepository
) : DataSource.Factory<Long, Car>() {

    val data = MutableLiveData<CarPoolDataSource>()

    override fun create(): DataSource<Long, Car> {
        return CarPoolDataSource(repository).also { source ->
            data.postValue(source)
        }
    }
}