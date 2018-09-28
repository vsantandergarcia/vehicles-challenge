package com.vsantander.vehicleschallenge.data.repository

import com.vsantander.vehicleschallenge.data.remote.RestClient
import com.vsantander.vehicleschallenge.data.remote.mapper.VehicleTOMapper
import com.vsantander.vehicleschallenge.domain.model.Vehicle
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepositoryImpl @Inject constructor(
        private val restClient: RestClient,
        private val mapper: VehicleTOMapper) : VehicleRepository {

    override fun getListVehicles(latitude1: String, longitude1: String,
                                 latitude2: String, longitude2: String): Single<List<Vehicle>> {
        return restClient.getListVehicles(latitude1, longitude1, latitude2, longitude2)
                .map { mapper.toEntity(it.data) }
    }

}