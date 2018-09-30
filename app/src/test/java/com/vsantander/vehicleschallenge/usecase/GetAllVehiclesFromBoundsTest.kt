package com.vsantander.vehicleschallenge.usecase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.vsantander.vehicleschallenge.data.repository.VehicleRepositoryImpl
import com.vsantander.vehicleschallenge.domain.usecase.GetAllVehiclesFromBounds
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
class GetAllVehiclesFromBoundsTest {

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
    lateinit var repository: VehicleRepositoryImpl

    lateinit var getAllVehiclesFromBounds: GetAllVehiclesFromBounds

    @Before
    fun setUp() {
        getAllVehiclesFromBounds = GetAllVehiclesFromBounds(repository)
    }

    @Test
    fun getAllVehiclesFromBoundsReturnVehicles() {
        val vehiclesList = VehicleFactory.makeVehicleList(NUMBER_VEHICLES)
        Mockito.`when`(repository.getListVehicles(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Single.just(vehiclesList))

        val params = GetAllVehiclesFromBounds.RequestValues(
                CoordinateFactory.makeCoordinateModel(), CoordinateFactory.makeCoordinateModel())
        val testObserver = getAllVehiclesFromBounds.buildUseCase(params).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val result = testObserver.values()[0]
        assert(result == vehiclesList)
    }

    @Test
    fun getAllVehiclesFromBoundsErrorReturnError() {
        Mockito.`when`(repository.getListVehicles(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Single.error { throw RuntimeException() })

        val params = GetAllVehiclesFromBounds.RequestValues(
                CoordinateFactory.makeCoordinateModel(), CoordinateFactory.makeCoordinateModel())
        val testObserver = getAllVehiclesFromBounds.buildUseCase(params).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertNotComplete()
        testObserver.assertError(RuntimeException::class.java)
    }
}