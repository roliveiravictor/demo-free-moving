package com.stonetree.freemoving.repositories

import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.core.model.Camera
import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.restclient.feature.repository.CoreRepository
import java.util.HashMap

interface JourneyRepository {

    fun get(): CoreRepository

    fun camera(args: JourneyViewArgs): Camera

    fun selectedCar(args: JourneyViewArgs): MarkerOptions
}
