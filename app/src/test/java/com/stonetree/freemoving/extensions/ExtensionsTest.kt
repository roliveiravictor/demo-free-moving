package com.stonetree.freemoving.extensions

import com.stonetree.freemoving.R
import com.stonetree.freemoving.enums.FleetType
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.feature.pool.model.Coordinate
import junit.framework.Assert.assertEquals
import org.junit.Test

class ExtensionsTest {

    @Test
    fun createMapMar_shouldReturnFilled() {
        Car(
            1,
            Coordinate(1.0, 1.0),
            "mFleet",
            1f
        ).apply {
            createMapMark().run {
                assertEquals(id.toString(), title)
                assertEquals(position.latitude, coordinate.latitude)
                assertEquals(position.longitude, coordinate.longitude)
            }
        }
    }

    @Test
    fun avatar_whenTaxi_shouldReturnTaxiDrawable() {
        FleetType.TAXI.name.avatar().run {
            assertEquals(R.drawable.ic_taxi, this)
        }
    }

    @Test
    fun avatar_whenDefault_shouldReturnTaxiDrawable() {
        "Any".avatar().run {
            assertEquals(R.drawable.ic_taxi, this)
        }
    }

    @Test
    fun avatar_whenPooling_shouldReturnTaxiDrawable() {
        FleetType.POOLING.name.avatar().run {
            assertEquals(R.drawable.ic_uber, this)
        }
    }
}