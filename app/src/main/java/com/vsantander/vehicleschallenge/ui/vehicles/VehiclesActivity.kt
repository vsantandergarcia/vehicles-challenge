package com.vsantander.vehicleschallenge.ui.vehicles

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.vsantander.vehicleschallenge.R
import com.vsantander.vehicleschallenge.domain.model.Coordinate
import com.vsantander.vehicleschallenge.extension.observe
import com.vsantander.vehicleschallenge.ui.base.activity.BaseActivity
import com.vsantander.vehicleschallenge.utils.Constants
import javax.inject.Inject

@BaseActivity.Animation(BaseActivity.FADE)
class VehiclesActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: VehiclesViewModel

    /* Activity methods */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles)
        setUpViews()
        setUpViewModels()
    }

    /* setUp methods */

    private fun setUpViews() {
    }

    private fun setUpViewModels() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(VehiclesViewModel::class.java)

        viewModel.resource.observe(this) {
            it ?: return@observe
        }

        val coordinate1 = Coordinate(Constants.LATITUDE1_HAMBURG, Constants.LONGUTUDE1_HAMBURG)
        val coordinate2 = Coordinate(Constants.LATITUDE2_HAMBURG, Constants.LONGITUDE2_HAMBURG)
        viewModel.loadVehiclesFromBounds(coordinate1, coordinate2)
    }
}