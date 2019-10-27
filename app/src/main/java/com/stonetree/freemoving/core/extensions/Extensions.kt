package com.stonetree.freemoving.core.extensions

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.feature.pool.model.Car

fun Car.createMapMark(): MarkerOptions {
    val position = LatLng(
        coordinate.latitude,
        coordinate.longitude
    )

    return MarkerOptions()
        .position(position)
        .title(id.toString())
}