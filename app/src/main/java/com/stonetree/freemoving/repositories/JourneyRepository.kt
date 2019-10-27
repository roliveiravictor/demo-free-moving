package com.stonetree.freemoving.repositories

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.core.model.Camera
import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.restclient.feature.repository.CoreRepository
import java.util.HashMap

interface JourneyRepository {

    fun get(): CoreRepository

    fun camera(args: JourneyViewArgs): Camera

    fun selectedCar(args: JourneyViewArgs): MarkerOptions

    fun saveLastPosition(lastCameraPos: CameraPosition)

    fun load(currentCameraPos: CameraPosition)

    fun marks(): MutableLiveData<MutableList<MarkerOptions>>
}
