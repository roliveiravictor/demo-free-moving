package com.stonetree.freemoving.feature.journey.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.freemoving.repositories.JourneyRepository
import com.stonetree.restclient.core.model.NetworkState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class JourneyViewModel(
    private val repository: JourneyRepository,
    private val args: JourneyViewArgs
) : ViewModel() {

    val network: LiveData<NetworkState> = repository.get().network()

    fun retry() = repository.get().retry()

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    init {
        viewModelScope.launch {
            repository.load(args)
        }
    }
}
