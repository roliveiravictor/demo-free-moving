package com.stonetree.freemoving.extensions

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.R
import com.stonetree.freemoving.enums.FleetType.*
import com.stonetree.freemoving.feature.pool.model.Car
import kotlin.math.abs

fun String.readFile(context: Context): String {
    context.assets.open(this)
        .bufferedReader()
        .use { buffer ->
            return buffer.readText()
        }
}

fun Car.createMapMark(): MarkerOptions {
    val position = LatLng(
        coordinate.latitude,
        coordinate.longitude
    )

    return MarkerOptions()
        .position(position)
        .title(id.toString())
}

fun List<MarkerOptions>.notStored(car: Car): Boolean {
    forEach { mark ->
        if (mark.title == car.id.toString())
            return false
    }
    return true
}

fun List<MarkerOptions>.safeDistance(car: Car): Boolean {
    if (isEmpty()) return true

    val dy = abs(car.coordinate.latitude - last().position.latitude) > 0.05
    val dx = abs(car.coordinate.longitude - last().position.longitude) > 0.05
    if (dx || dy)
        return true

    return false
}

fun String.avatar(): Int {
    return when (this) {
        TAXI.name -> R.drawable.ic_taxi
        POOLING.name -> R.drawable.ic_uber
        else -> R.drawable.ic_taxi
    }
}