package com.stonetree.freemoving.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.apis.CarPoolApi
import com.stonetree.freemoving.extensions.createMapMark
import com.stonetree.freemoving.extensions.notStored
import com.stonetree.freemoving.extensions.safeDistance
import com.stonetree.freemoving.models.Camera
import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.freemoving.feature.pool.model.CarPool
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.feature.RestClient
import retrofit2.Call

class JourneyRepositoryImpl(
    client: RestClient
) : MainRepository(), JourneyRepository {

    private val api: CarPoolApi = client.generate(CarPoolApi::class)

    private lateinit var lastCameraPos: CameraPosition

    private lateinit var request: Call<CarPool>

    private val marks = MutableLiveData<MutableList<MarkerOptions>>(arrayListOf())

    override fun get() = this

    override fun camera(args: JourneyViewArgs) = Camera(args.car.coordinate)

    override fun selectedCar(args: JourneyViewArgs) = args.car.createMapMark()

    override fun saveLastPosition(lastCameraPos: CameraPosition) {
        this.lastCameraPos = lastCameraPos
    }

    override fun marks(): MutableLiveData<MutableList<MarkerOptions>> = marks

    override fun load(currentCameraPos: CameraPosition) {
        request = assembleLoadRequest(currentCameraPos)
        request.enqueue(network()) {
            onResponse = { response ->
                marks.value?.apply {
                    response.body()?.poiList?.let { pool ->
                        pool.forEach { car ->
                            /*  Cars should not be stored in memory, but in ROM.       */
                            /*  This is just to prove that similar logic should apply. */
                            /*  Random positions sent from API shouldn't be so close.  */
                            /*  Also applying logic to reduce this incidence overflow. */
                            if (notStored(car) && safeDistance(car))
                                add(car.createMapMark())
                        }

                        /*  Also applying logic to reduce memory consumption. */
                        val threshold = size / 4
                        for (i in 1..threshold) remove(first())

                        marks.postValue(this)
                    }
                }
            }

            onFailure = { error ->
                error?.apply {
                    Log.e(javaClass.name, message.toString())
                }
            }
        }
    }

    private fun assembleLoadRequest(currentCameraPos: CameraPosition): Call<CarPool> {
        lastCameraPos.target.let { last ->
            currentCameraPos.target.let { current ->
                return api.get(
                    last.latitude,
                    current.latitude,
                    last.longitude,
                    current.longitude
                )
            }
        }
    }
}
