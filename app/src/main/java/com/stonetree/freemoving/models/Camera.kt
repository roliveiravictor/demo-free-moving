package com.stonetree.freemoving.models

import com.google.android.gms.maps.model.LatLng
import com.stonetree.freemoving.feature.pool.model.Coordinate

class Camera(private val coordinate: Coordinate) {

    fun position(): LatLng {
        coordinate.apply {
            return LatLng(latitude, longitude)
        }
    }
}