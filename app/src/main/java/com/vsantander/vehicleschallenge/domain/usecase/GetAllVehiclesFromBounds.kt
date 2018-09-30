package com.vsantander.vehicleschallenge.domain.usecase

import com.vsantander.vehicleschallenge.data.repository.VehicleRepositoryImpl
import com.vsantander.vehicleschallenge.domain.model.Coordinate
import com.vsantander.vehicleschallenge.domain.model.Vehicle
import com.vsantander.vehicleschallenge.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAllVehiclesFromBounds @Inject constructor(
        private val repository: VehicleRepositoryImpl
) : SingleUseCase<GetAllVehiclesFromBounds.RequestValues, List<Vehicle>>() {

    class RequestValues constructor(
            val coordinate1: Coordinate,
            val coordinate2: Coordinate
    )

    override fun buildUseCase(params: RequestValues?): Single<List<Vehicle>> {
        return repository.getListVehicles(params!!.coordinate1.latitude.toString(),
                params.coordinate1.longitude.toString(),
                params.coordinate2.latitude.toString(),
                params.coordinate2.longitude.toString())
    }

}