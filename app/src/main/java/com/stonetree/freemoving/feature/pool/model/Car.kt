package com.stonetree.freemoving.feature.pool.model

import java.io.Serializable

data class Car(
    val id: Long,
    val coordinate: Coordinate,
    val fleetType: String,
    val heading: Float
) : Serializable

