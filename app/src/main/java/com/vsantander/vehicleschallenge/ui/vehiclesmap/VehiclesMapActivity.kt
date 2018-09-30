package com.vsantander.vehicleschallenge.ui.vehiclesmap

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import androidx.core.view.isVisible
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vsantander.vehicleschallenge.R
import com.vsantander.vehicleschallenge.domain.model.Coordinate
import com.vsantander.vehicleschallenge.domain.model.Status
import com.vsantander.vehicleschallenge.domain.model.Vehicle
import com.vsantander.vehicleschallenge.extension.observe
import com.vsantander.vehicleschallenge.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_vehicles_map.*
import org.jetbrains.anko.contentView
import javax.inject.Inject


@BaseActivity.Animation(BaseActivity.PUSH)
class VehiclesMapActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    companion object {
        const val EXTRA_VEHICLE = "EXTRA_VEHICLE"
        const val ZOOM_MAP_LEVEL = 17f
    }

    private var vehicle: Vehicle? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: VehiclesMapViewModel

    private var mapFragment: SupportMapFragment? = null
    private var googleMap: GoogleMap? = null

    /* Activity methods */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles_map)

        intent?.extras?.getParcelable<Vehicle>(EXTRA_VEHICLE)?.let {
            vehicle = it
        }

        setUpViews()
        setUpViewModels()
    }

    /* setUp methods */

    private fun setUpViews() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }


    private fun setUpViewModels() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(VehiclesMapViewModel::class.java)

        viewModel.resource.observe(this) {
            it ?: return@observe

            progressBar.isVisible = it.status == Status.LOADING

            if (it.status == Status.SUCCESS) {
                bindVehicles(it.data!!)
            } else if (it.status == Status.FAILED) {
                Snackbar.make(contentView!!, R.string.common_error, Snackbar.LENGTH_LONG)
                        .show()
            }
        }
    }

    /* OnMapReadyCallback methods */

    override fun onMapReady(map: GoogleMap?) {
        map ?: return
        googleMap = map

        vehicle?.let { vehicle ->
            googleMap?.apply {
                val latlng = LatLng(vehicle.coordinate.latitude, vehicle.coordinate.longitude)
                moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, ZOOM_MAP_LEVEL))
                bindVehicle(vehicle)
            }
        } ?: googleMap?.setOnCameraIdleListener(this)
    }

    /* GoogleMap.OnCameraIdleListener methods */

    override fun onCameraIdle() {
        val bounds = googleMap?.projection?.visibleRegion?.latLngBounds
        bounds?.let {
            val coordinate1 = Coordinate(it.northeast.latitude, it.northeast.longitude)
            val coordinate2 = Coordinate(it.southwest.latitude, it.southwest.longitude)
            viewModel.loadVehiclesFromBounds(coordinate1, coordinate2)
        }
    }

    /* Owner methods */

    private fun bindVehicles(vehicles: List<Vehicle>) {
        vehicles.forEach { bindVehicle(it) }
    }

    private fun bindVehicle(vehicle: Vehicle) {
        val latlng = LatLng(vehicle.coordinate.latitude, vehicle.coordinate.longitude)
        googleMap?.apply {
            addMarker(MarkerOptions().position(latlng))!!.title = vehicle.id
        }
    }
}