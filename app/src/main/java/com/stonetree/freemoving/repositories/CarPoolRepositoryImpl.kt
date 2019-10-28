package com.stonetree.freemoving.repositories

import android.util.Log
import com.stonetree.freemoving.apis.CarPoolApi
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.feature.pool.model.CarPool
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.feature.RestClient
import retrofit2.Call

class CarPoolRepositoryImpl(
    private val client: RestClient
) : MainRepository(), CarPoolRepository {
    
    private val api: CarPoolApi = client.generate(CarPoolApi::class)

    private lateinit var request: Call<CarPool>

    override fun get() = this

    override fun load(callback: List<Car>.() -> Unit) {
        request = api.get()
        request.enqueue(network()) {
            onResponse = { response ->
                response.body()?.poiList?.let { pool ->
                    callback.invoke(pool)
                }
            }

            onFailure = { error ->
                error?.apply {
                    Log.e(javaClass.name, message.toString())
                }
            }
        }
    }
}