package com.stonetree.freemoving.core.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.stonetree.freemoving.feature.pool.model.Coordinate

class Camera(private val coordinate: Coordinate) {

    fun position(): LatLng {
        coordinate.apply {
            return LatLng(latitude, longitude)
        }
    }
}