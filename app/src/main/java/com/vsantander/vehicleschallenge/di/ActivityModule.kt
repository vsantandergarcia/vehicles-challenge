package com.vsantander.vehicleschallenge.di

import com.vsantander.vehicleschallenge.ui.splash.SplashActivity
import com.vsantander.vehicleschallenge.ui.vehicles.VehiclesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [(ViewModelModule::class)])
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun contributeVehiclesActivity(): VehiclesActivity
}