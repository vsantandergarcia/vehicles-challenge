package com.vsantander.vehicleschallenge.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vsantander.vehicleschallenge.ui.base.viewmodel.ViewModelFactory
import com.vsantander.vehicleschallenge.ui.vehicleslist.VehiclesListViewModel
import com.vsantander.vehicleschallenge.ui.vehiclesmap.VehiclesMapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(VehiclesListViewModel::class)
    abstract fun bindVehiclesListViewModel(viewModel: VehiclesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VehiclesMapViewModel::class)
    abstract fun bindVehiclesMapViewModel(viewModel: VehiclesMapViewModel): ViewModel
}