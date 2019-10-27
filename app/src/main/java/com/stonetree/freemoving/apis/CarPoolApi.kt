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
        @Query(P1_LAT) p1Lat: Double = 53.694865,
        @Query(P2_LAT) p2Lat: Double = 53.394655,
        @Query(P1_LON) p1Lon: Double = 9.757589,
        @Query(P2_LON) p2Lon: Double = 10.099891
    ): Call<CarPool>
}