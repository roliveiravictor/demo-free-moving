package com.stonetree.freemoving.feature.journey.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.freemoving.repositories.JourneyRepository
import com.stonetree.restclient.core.model.NetworkState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel

class JourneyViewModel(
    private val repository: JourneyRepository,
    private val args: JourneyViewArgs
) : ViewModel() {

    val latitude: Float = 51.513259F
    val longitude: Float = -0.129147F

    val network: LiveData<NetworkState> = repository.get().network()

    fun retry() = repository.get().retry()

    fun camera() = repository.camera(args)

    fun selectedCar(): MarkerOptions = repository.selectedCar(args)

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
