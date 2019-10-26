package com.stonetree.freemoving.repositories

import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.restclient.feature.RestClient

class JourneyRepositoryImpl(rest: RestClient) : MainRepository(), JourneyRepository {

    override fun get() = this

    override fun retry() {
    }

    override fun load(args: JourneyViewArgs) {

    }
}