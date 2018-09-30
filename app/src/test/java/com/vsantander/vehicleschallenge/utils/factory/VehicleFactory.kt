package com.vsantander.vehicleschallenge.utils.factory

import com.vsantander.vehicleschallenge.domain.model.Coordinate
import com.vsantander.vehicleschallenge.domain.model.Vehicle

/**
 * Factory class for Vehicle related instances
 */
class VehicleFactory {

    companion object {

        fun makeVehicleList(count: Int): List<Vehicle> {
            val vehicleList = mutableListOf<Vehicle>()
            repeat(count) {
                vehicleList.add(makeVehicleModel())
            }
            return vehicleList
        }

        private fun makeVehicleModel(): Vehicle {
            return Vehicle(
                    id = DataFactory.randomUuid(),
                    coordinate = Coordinate(
                            latitude = DataFactory.randomDouble(),
                            longitude = DataFactory.randomDouble()
                    ),
                    type = DataFactory.randomUuid()
            )
        }
    }
}