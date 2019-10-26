package com.stonetree.freemoving.directions

import android.os.Bundle
import androidx.navigation.NavDirections
import com.stonetree.freemoving.R
import com.stonetree.freemoving.core.constants.Constants.Bundle.DRIVER_ID
import com.stonetree.freemoving.feature.pool.model.Car

class CarPoolDirections private constructor() {

    private data class ActionCarPoolToJourney(val car: Car) : NavDirections {
        override fun getActionId(): Int = R.id.view_journey

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putString(DRIVER_ID, car.toString())
            return result
        }
    }

    companion object {
        fun actionLatestToDetails(car: Car): NavDirections = ActionCarPoolToJourney(car)
    }
}