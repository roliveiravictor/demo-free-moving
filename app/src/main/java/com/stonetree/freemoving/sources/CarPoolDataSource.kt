package com.stonetree.freemoving.sources

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.repositories.CarPoolRepository

class CarPoolDataSource(
    private val repository: CarPoolRepository
) : PageKeyedDataSource<Long, Car>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Car>
    ) {
        repository.load {
            callback.onResult(this, null, null)
        }
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, Car>
    ) {
        Log.w(javaClass.name, params.key.toString())
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Car>) {
        Log.w(javaClass.name, params.key.toString())
    }
}