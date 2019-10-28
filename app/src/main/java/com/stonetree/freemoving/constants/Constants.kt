package com.stonetree.freemoving.constants

object Constants {

    object Map {

        const val MAX_MARKS_REFERENCE = 50

        object Camera {
            const val ZOOM_DISTANCE = 10f
        }
    }

    object Params {

        object CarPool {
            const val P1_LAT = "p1Lat"
            const val P1_LON = "p1Lon"

            const val P2_LAT = "p2Lat"
            const val P2_LON = "p2Lon"
        }
    }

    object Actions {
        const val NAVIGATOR = "com.stonetree.freemoving.NavigatorActivity"
    }

    object Bundle {

        const val CAR: String = "car"
    }
}