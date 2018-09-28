package com.vsantander.vehicleschallenge.utils.factory

import com.vsantander.vehicleschallenge.domain.model.Coordinate

class CoordinateFactory {

    companion object {

        fun makeCoordinateModel(): Coordinate {
            return Coordinate(
                    latitude = DataFactory.randomFloat(),
                    longitude = DataFactory.randomFloat()
            )
        }

    }

}