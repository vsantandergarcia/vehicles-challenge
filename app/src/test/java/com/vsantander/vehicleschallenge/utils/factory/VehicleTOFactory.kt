package com.vsantander.vehicleschallenge.utils.factory

import com.vsantander.vehicleschallenge.data.remote.model.CoordinateTO
import com.vsantander.vehicleschallenge.data.remote.model.VehicleTO

/**
 * Factory class for VehicleTO related instances
 */
class VehicleTOFactory {

    companion object {

        fun makeVehicleTOList(count: Int): List<VehicleTO> {
            val vehicleList = mutableListOf<VehicleTO>()
            repeat(count) {
                vehicleList.add(makeVehicleTOModel())
            }
            return vehicleList
        }

        private fun makeVehicleTOModel(): VehicleTO {
            return VehicleTO(
                    id = DataFactory.randomUuid(),
                    coordinate = CoordinateTO(
                            latitude = DataFactory.randomDouble(),
                            longitude = DataFactory.randomDouble()
                    ),
                    type = DataFactory.randomUuid(),
                    heading = DataFactory.randomFloat()
            )
        }
    }

}