package com.stonetree.freemoving.repositories

import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.restclient.feature.repository.CoreRepository

interface JourneyRepository {

    fun get(): CoreRepository

    fun load(args: JourneyViewArgs)
}
