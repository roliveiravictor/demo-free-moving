package com.stonetree.freemoving.directions

import android.os.Bundle
import androidx.navigation.NavDirections
import com.stonetree.freemoving.R
import com.stonetree.freemoving.core.constants.Constants.Bundle.DRIVER_ID

class CarPoolDirections private constructor() {

    private data class ActionCarPoolToJourney(val id: Long) : NavDirections {
        override fun getActionId(): Int = R.id.view_journey

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putLong(DRIVER_ID, this.id)
            return result
        }
    }

    companion object {
        fun actionLatestToDetails(id: Long): NavDirections = ActionCarPoolToJourney(id)
    }
}