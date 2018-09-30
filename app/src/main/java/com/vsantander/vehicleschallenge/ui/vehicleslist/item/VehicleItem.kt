package com.vsantander.vehicleschallenge.ui.vehicleslist.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vsantander.vehicleschallenge.R
import com.vsantander.vehicleschallenge.domain.model.Vehicle

import com.vsantander.vehicleschallenge.ui.base.item.ItemView
import com.vsantander.vehicleschallenge.utils.Constants
import kotlinx.android.synthetic.main.view_item_vehicle.view.*
import org.jetbrains.anko.dimen

class VehicleItem @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ItemView<Vehicle>(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_item_vehicle, this, true)
        useCompatPadding = true
        radius = context.dimen(R.dimen.vehicle_item_radius).toFloat()
        cardElevation = context.dimen(R.dimen.vehicle_item_elevation).toFloat()
    }

    override fun bind(item: Vehicle) {
        titleTextView.text = item.id

        Glide
                .with(context)
                .load(item.imageResource)
                .transition(DrawableTransitionOptions.withCrossFade(Constants.DURATION_FADE_GLIDE))
                .into(vehicleImageView)
    }
}