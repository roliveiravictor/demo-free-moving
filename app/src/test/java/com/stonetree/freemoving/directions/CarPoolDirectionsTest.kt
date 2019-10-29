package com.stonetree.freemoving.directions

import com.stonetree.freemoving.R
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.feature.pool.model.Coordinate
import junit.framework.Assert.assertEquals
import org.junit.Test

class CarPoolDirectionsTest {

    private val dummy = Car(
        1,
        Coordinate(1.0, 1.0),
        "mFleet",
        1f
    )

    @Test
    fun actionPoolToJourney_shouldReturnJourneyId() {
        CarPoolDirections.actionCarPoolToJourney(dummy).run {
            assertEquals(this, R.id.view_journey)
        }
    }
}
