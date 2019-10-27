package com.stonetree.freemoving.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.freemoving.apis.CarPoolApi
import com.stonetree.freemoving.core.extensions.createMapMark
import com.stonetree.freemoving.core.extensions.notStored
import com.stonetree.freemoving.core.model.Camera
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
                            if(notStored(car))
                                add(car.createMapMark())
                        }
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
