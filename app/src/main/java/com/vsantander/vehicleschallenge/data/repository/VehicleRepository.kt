package com.vsantander.vehicleschallenge.data.repository

import com.vsantander.vehicleschallenge.domain.model.Vehicle
import io.reactivex.Single

interface VehicleRepository {

    /**
     * Gets a list of Vehicle
     *
     * @param latitude1: latitude first bound
     * @param longitude1: longitude first bound
     * @param latitude2: latitude second bound
     * @param longitude2: longitude second bound
     *
     * @return a List of the Vehicles available.
     */
    fun getListVehicles(latitude1: String,
                        longitude1: String,
                        latitude2: String,
                        longitude2: String): Single<List<Vehicle>>

}