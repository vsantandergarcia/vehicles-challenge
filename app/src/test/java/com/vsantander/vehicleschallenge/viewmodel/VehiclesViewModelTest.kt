package com.vsantander.vehicleschallenge.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.vsantander.vehicleschallenge.domain.model.Resource
import com.vsantander.vehicleschallenge.domain.model.Status
import com.vsantander.vehicleschallenge.domain.model.Vehicle
import com.vsantander.vehicleschallenge.domain.usecase.norm.GetAllVehiclesFromBounds
import com.vsantander.vehicleschallenge.ui.vehicleslist.VehiclesListViewModel
import com.vsantander.vehicleschallenge.utils.RxImmediateSchedulerRule
import com.vsantander.vehicleschallenge.utils.factory.CoordinateFactory
import com.vsantander.vehicleschallenge.utils.factory.VehicleFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VehiclesViewModelTest {

    companion object {
        private const val NUMBER_VEHICLES = 5
    }

    @Suppress("unused")
    @get:Rule // used to make all live data calls sync
    val instantExecutor = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var getAllVehiclesFromBounds: GetAllVehiclesFromBounds

    @Mock
    lateinit var observer: Observer<Resource<List<Vehicle>>>

    lateinit var listViewModel: VehiclesListViewModel

    @Before
    fun setUp() {
        listViewModel = VehiclesListViewModel(getAllVehiclesFromBounds)
    }

    @Test
    fun loadVehiclesFromBoundsLoadingState() {
        val vehiclesList = VehicleFactory.makeVehicleList(NUMBER_VEHICLES)
        Mockito.`when`(getAllVehiclesFromBounds.buildUseCase(Mockito.any()))
                .thenReturn(Single.just(vehiclesList))
        listViewModel.resource.observeForever(observer)

        listViewModel.loadVehiclesFromBounds(CoordinateFactory.makeCoordinateModel(),
                CoordinateFactory.makeCoordinateModel())

        Mockito.verify(observer).onChanged(Resource(Status.LOADING))
    }

    @Test
    fun loadVehiclesFromBoundsSuccessState() {
        val vehiclesList = VehicleFactory.makeVehicleList(NUMBER_VEHICLES)
        Mockito.`when`(getAllVehiclesFromBounds.buildUseCase(Mockito.any()))
                .thenReturn(Single.just(vehiclesList))

        listViewModel.loadVehiclesFromBounds(CoordinateFactory.makeCoordinateModel(),
                CoordinateFactory.makeCoordinateModel())

        assert(listViewModel.resource.value?.status == Status.SUCCESS)
    }

    @Test
    fun loadVehiclesFromBoundsErrorState() {
        Mockito.`when`(getAllVehiclesFromBounds.buildUseCase(Mockito.any()))
                .thenReturn(Single.error { throw RuntimeException() })

        listViewModel.loadVehiclesFromBounds(CoordinateFactory.makeCoordinateModel(),
                CoordinateFactory.makeCoordinateModel())

        assert(listViewModel.resource.value?.status == Status.FAILED)
    }

    @Test
    fun loadVehiclesFromBoundsReturnVehicles() {
        val vehiclesList = VehicleFactory.makeVehicleList(NUMBER_VEHICLES)
        Mockito.`when`(getAllVehiclesFromBounds.buildUseCase(Mockito.any()))
                .thenReturn(Single.just(vehiclesList))

        listViewModel.loadVehiclesFromBounds(CoordinateFactory.makeCoordinateModel(),
                CoordinateFactory.makeCoordinateModel())

        assert(listViewModel.resource.value?.data == vehiclesList)
    }
}