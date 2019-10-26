package com.stonetree.restclient.feature.repository

import androidx.lifecycle.MutableLiveData
import com.stonetree.restclient.core.model.NetworkState

abstract class CoreRepository : Repository {

    override fun network() = MutableLiveData<NetworkState>()

    override fun retry() {

    }
}