package com.stonetree.freemoving.feature.journey.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.freemoving.repositories.JourneyRepository

class JourneyViewModel(
    private val repository: JourneyRepository,
    private val args: JourneyViewArgs
) : ViewModel() {

    val marks: LiveData<MutableList<MarkerOptions>> = repository.marks()

    fun retry() = repository.get().retry()

    fun camera() = repository.camera(args)

    fun selectedCar(): MarkerOptions = repository.selectedCar(args)

    fun saveLastPosition(pos: CameraPosition) = repository.saveLastPosition(pos)

    fun load(cameraPosition: CameraPosition) = repository.load(cameraPosition)
}
