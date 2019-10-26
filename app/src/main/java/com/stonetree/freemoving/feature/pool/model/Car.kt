package com.stonetree.freemoving.feature.pool.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder

data class Car(
    val id: Long,
    val coordinate: Coordinate,
    val fleetType: String,
    val heading: Float
) {

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
