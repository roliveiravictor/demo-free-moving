package com.stonetree.freemoving.apis

import com.stonetree.freemoving.core.constants.Constants.Params.CarPool.P1_LAT
import com.stonetree.freemoving.core.constants.Constants.Params.CarPool.P1_LON
import com.stonetree.freemoving.core.constants.Constants.Params.CarPool.P2_LAT
import com.stonetree.freemoving.core.constants.Constants.Params.CarPool.P2_LON
import com.stonetree.freemoving.feature.pool.model.CarPool
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CarPoolApi {

    @GET("/")
    fun get(
        @Query(P1_LAT) p1Lat: Float = 53.694865F,
        @Query(P2_LAT) p2Lat: Float = 53.394655F,
        @Query(P1_LON) p1Lon: Float = 9.757589F,
        @Query(P2_LON) p2Lon: Float = 10.099891F
    ): Call<CarPool>
}