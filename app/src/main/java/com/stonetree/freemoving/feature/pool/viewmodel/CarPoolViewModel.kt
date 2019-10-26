package com.stonetree.freemoving.feature.pool.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.feature.pool.model.CarPool
import com.stonetree.freemoving.repositories.CarPoolRepository
import com.stonetree.freemoving.sources.factories.CarPoolSourceFactory
import com.stonetree.restclient.core.constants.RestclientConstants
import com.stonetree.restclient.core.model.NetworkState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import java.util.concurrent.Executors

class CarPoolViewModel(
    private val repository: CarPoolRepository,
    private val factory: CarPoolSourceFactory
) : ViewModel() {

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(RestclientConstants.PAGE_SIZE)
        .setPageSize(RestclientConstants.PAGE_SIZE)
        .setPrefetchDistance(RestclientConstants.PRE_FETCH_DISTANCE)
        .setEnablePlaceholders(false)
        .build()

    val pool: LiveData<PagedList<Car>> =
        LivePagedListBuilder(factory, config)
            .setFetchExecutor(Executors.newFixedThreadPool(RestclientConstants.MAX_THREADS))
            .build()

    val network: LiveData<NetworkState> = repository.network()

    fun retry() = factory.data.value?.invalidate()

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}