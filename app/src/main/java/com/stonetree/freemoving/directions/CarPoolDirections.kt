package com.stonetree.freemoving.directions

import android.os.Bundle
import androidx.navigation.NavDirections
import com.stonetree.freemoving.R
import com.stonetree.freemoving.constants.Constants.Bundle.CAR
import com.stonetree.freemoving.feature.pool.model.Car

class CarPoolDirections private constructor() {

    private data class ActionCarPoolToJourney(val car: Car) : NavDirections {
        override fun getActionId(): Int = R.id.view_journey

        override fun getArguments(): Bundle {
            return Bundle().apply {
                putSerializable(CAR, car)
            }
        }
    }

    companion object {
        fun actionCarPoolToJourney(car: Car): NavDirections = ActionCarPoolToJourney(car)
    }
}