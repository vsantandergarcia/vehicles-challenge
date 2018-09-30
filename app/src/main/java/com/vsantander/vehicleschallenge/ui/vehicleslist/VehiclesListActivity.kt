package com.vsantander.vehicleschallenge.ui.vehicleslist

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import androidx.core.view.isVisible
import com.vsantander.vehicleschallenge.R
import com.vsantander.vehicleschallenge.domain.model.Coordinate
import com.vsantander.vehicleschallenge.domain.model.Status
import com.vsantander.vehicleschallenge.extension.logd
import com.vsantander.vehicleschallenge.extension.observe
import com.vsantander.vehicleschallenge.ui.base.activity.BaseActivity
import com.vsantander.vehicleschallenge.ui.vehicleslist.adapter.VehiclesAdapter
import com.vsantander.vehicleschallenge.ui.vehiclesmap.VehiclesMapActivity
import com.vsantander.vehicleschallenge.utils.Constants
import kotlinx.android.synthetic.main.activity_vehicles_list.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

@BaseActivity.Animation(BaseActivity.FADE)
class VehiclesListActivity : BaseActivity() {

    companion object {
        private const val LIST_SPAN_COUNT = 2
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: VehiclesListViewModel

    private lateinit var adapter: VehiclesAdapter

    /* Activity methods */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles_list)
        setUpViews()
        setUpViewModels()
    }

    /* setUp methods */

    private fun setUpViews() {
        setUpToolbar()

        adapter = VehiclesAdapter().apply {
            onClickAction = {
                logd("item vehicle click with id:${it}")
                startActivity<VehiclesMapActivity>(Pair(VehiclesMapActivity.EXTRA_VEHICLE, it))
            }
        }

        swipeRefreshLayout.setOnRefreshListener { loadInfo() }
        recyclerView.apply {
            layoutManager = GridLayoutManager(context,
                    LIST_SPAN_COUNT) as RecyclerView.LayoutManager
            adapter = this@VehiclesListActivity.adapter
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mapButton.setOnClickListener {
            logd("map toolbar button click")
            startActivity<VehiclesMapActivity>()
        }
    }

    private fun setUpViewModels() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(VehiclesListViewModel::class.java)

        viewModel.resource.observe(this) {
            it ?: return@observe

            progressBar.isVisible = it.status == Status.LOADING
            swipeRefreshLayout.isRefreshing = it == Status.LOADING

            if (it.status == Status.SUCCESS) {
                adapter.setItems(it.data!!)
            } else if (it.status == Status.FAILED) {
                Snackbar.make(recyclerView, R.string.common_error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.retry) { loadInfo() }
                        .show()
            }
        }

        loadInfo()
    }

    /* Owner methods */

    private fun loadInfo() {
        val coordinate1 = Coordinate(Constants.LATITUDE1_HAMBURG, Constants.LONGUTUDE1_HAMBURG)
        val coordinate2 = Coordinate(Constants.LATITUDE2_HAMBURG, Constants.LONGITUDE2_HAMBURG)
        viewModel.loadVehiclesFromBounds(coordinate1, coordinate2)
    }
}