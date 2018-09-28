package com.vsantander.vehicleschallenge.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vsantander.vehicleschallenge.ui.base.viewmodel.ViewModelFactory
import com.vsantander.vehicleschallenge.ui.vehicles.VehiclesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(VehiclesViewModel::class)
    abstract fun bindVehiclesViewModel(viewModel: VehiclesViewModel): ViewModel
}