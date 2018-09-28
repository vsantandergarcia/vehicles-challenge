package com.vsantander.vehicleschallenge.data.remote

import com.vsantander.vehicleschallenge.data.remote.model.DefaultResponse
import com.vsantander.vehicleschallenge.data.remote.model.VehicleTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestClient {

    @GET("?")
    fun getListVehicles(
            @Query("p1Lat") latitude1: String,
            @Query("p1Lon") longitude1: String,
            @Query("p2Lat") latitude2: String,
            @Query("p2Lon") longitude2: String
    ): Single<DefaultResponse<List<VehicleTO>>>

}