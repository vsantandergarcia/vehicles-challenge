package com.vsantander.vehicleschallenge.data.remote.mapper

import com.vsantander.vehicleschallenge.data.remote.model.VehicleTO
import com.vsantander.vehicleschallenge.domain.model.Coordinate
import com.vsantander.vehicleschallenge.domain.model.Vehicle
import javax.inject.Inject

class VehicleTOMapper @Inject constructor() {

    fun toEntity(value: VehicleTO): Vehicle {
        return Vehicle(
                id = value.id,
                coordinate = Coordinate(latitude = value.coordinate.latitude,
                        longitude = value.coordinate.longitude),
                type = value.type)
    }

    fun toEntity(values: List<VehicleTO>): List<Vehicle> = values.map { toEntity(it) }
}