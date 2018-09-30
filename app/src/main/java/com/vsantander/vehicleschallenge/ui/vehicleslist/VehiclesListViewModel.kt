package com.vsantander.vehicleschallenge.ui.vehicleslist

import android.arch.lifecycle.MutableLiveData
import com.vsantander.vehicleschallenge.domain.model.Coordinate
import com.vsantander.vehicleschallenge.domain.model.Resource
import com.vsantander.vehicleschallenge.domain.model.Vehicle
import com.vsantander.vehicleschallenge.domain.usecase.norm.GetAllVehiclesFromBounds
import com.vsantander.vehicleschallenge.extension.logd
import com.vsantander.vehicleschallenge.extension.loge
import com.vsantander.vehicleschallenge.ui.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VehiclesListViewModel @Inject constructor(
        private val getAllVehiclesFromBounds: GetAllVehiclesFromBounds
) : BaseViewModel() {

    val resource = MutableLiveData<Resource<List<Vehicle>>>()

    fun loadVehiclesFromBounds(coordinate1: Coordinate, coordinate2: Coordinate) {
        resource.value = Resource.loading()

        val params = GetAllVehiclesFromBounds.RequestValues(coordinate1,coordinate2)
        disposables += getAllVehiclesFromBounds.buildUseCase(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            logd("loadVehiclesFromBounds.onSuccess")
                            resource.value = Resource.success(it)
                        },
                        onError = {
                            loge("loadVehiclesFromBounds.onError", it)
                            resource.value = Resource.error(it)
                        }
                )
    }
}