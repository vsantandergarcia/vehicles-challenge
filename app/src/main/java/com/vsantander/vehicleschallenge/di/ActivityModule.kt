package com.vsantander.vehicleschallenge.di

import com.vsantander.vehicleschallenge.ui.splash.SplashActivity
import com.vsantander.vehicleschallenge.ui.vehicleslist.VehiclesListActivity
import com.vsantander.vehicleschallenge.ui.vehiclesmap.VehiclesMapActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [(ViewModelModule::class)])
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun contributeVehiclesActivity(): VehiclesListActivity

    @ContributesAndroidInjector
    internal abstract fun contributeVehiclesMapActivity(): VehiclesMapActivity
}