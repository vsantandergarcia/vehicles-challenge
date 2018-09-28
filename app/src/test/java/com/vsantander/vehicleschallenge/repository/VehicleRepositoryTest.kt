package com.vsantander.vehicleschallenge.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.vsantander.vehicleschallenge.data.remote.RestClient
import com.vsantander.vehicleschallenge.data.remote.mapper.VehicleTOMapper
import com.vsantander.vehicleschallenge.data.remote.model.DefaultResponse
import com.vsantander.vehicleschallenge.data.repository.VehicleRepositoryImpl
import com.vsantander.vehicleschallenge.utils.RxImmediateSchedulerRule
import com.vsantander.vehicleschallenge.utils.factory.VehicleTOFactory
import io.reactivex.Single
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VehicleRepositoryTest {

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
    lateinit var service: RestClient

    lateinit var mapper: VehicleTOMapper

    lateinit var repository: VehicleRepositoryImpl

    @Before
    fun setUp() {
        mapper = VehicleTOMapper()
        repository = VehicleRepositoryImpl(service, mapper)
    }

    @Test
    fun getListVehiclesReturnVehicles() {
        val vehicleTOList = VehicleTOFactory.makeVehicleTOList(NUMBER_VEHICLES)
        val vehicleList = vehicleTOList.map { mapper.toEntity(it) }
        Mockito.`when`(service.getListVehicles(Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(Single.just(DefaultResponse(vehicleTOList)))

        val testObserver = repository.getListVehicles(Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString()).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val result = testObserver.values()[0]
        MatcherAssert.assertThat(result.size, CoreMatchers.`is`(NUMBER_VEHICLES))
        assert(result == vehicleList)
    }

    @Test
    fun getListVehiclesErrorReturnError() {
        Mockito.`when`(service.getListVehicles(Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(Single.error { throw RuntimeException() })

        val testObserver = repository.getListVehicles(Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString()).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertNotComplete()
        testObserver.assertError(RuntimeException::class.java)
    }

}