package com.stonetree.freemoving.feature.pool.model

data class Car(
    val id: Long,
    val coordinate: Coordinate,
    val fleetType: String,
    val heading: Float
)
