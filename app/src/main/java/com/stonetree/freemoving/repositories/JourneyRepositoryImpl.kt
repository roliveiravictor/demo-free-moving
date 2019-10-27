package com.stonetree.freemoving.repositories

import com.stonetree.freemoving.core.extensions.createMapMark
import com.stonetree.freemoving.core.model.Camera
import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.restclient.feature.RestClient

class JourneyRepositoryImpl(rest: RestClient) : MainRepository(), JourneyRepository {

    override fun get() = this

    override fun retry() {
    }

    override fun camera(args: JourneyViewArgs) = Camera(args.car.coordinate)

    override fun selectedCar(args: JourneyViewArgs) = args.car.createMapMark()
}