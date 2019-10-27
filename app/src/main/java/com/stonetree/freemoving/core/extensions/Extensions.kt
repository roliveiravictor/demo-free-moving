package com.stonetree.freemoving.core.extensions

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.R
import com.stonetree.freemoving.core.enums.FleetType.*
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

fun String.avatar(): Int {
    return when (this) {
        TAXI.name -> R.drawable.ic_taxi
        POOLING.name -> R.drawable.ic_uber
        else -> R.drawable.ic_taxi
    }
}