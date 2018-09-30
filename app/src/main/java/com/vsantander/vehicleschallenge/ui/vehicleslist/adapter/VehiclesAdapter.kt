package com.vsantander.vehicleschallenge.ui.vehicleslist.adapter

import android.view.ViewGroup
import com.vsantander.vehicleschallenge.domain.model.Vehicle
import com.vsantander.vehicleschallenge.ui.base.adapter.RecyclerViewAdapterBase
import com.vsantander.vehicleschallenge.ui.base.adapter.ViewWrapper
import com.vsantander.vehicleschallenge.ui.base.item.ItemView
import com.vsantander.vehicleschallenge.ui.vehicleslist.item.VehicleItem

class VehiclesAdapter : RecyclerViewAdapterBase<Vehicle, ItemView<Vehicle>>() {

    var onClickAction: ((item: Vehicle) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ItemView<Vehicle> {
        return VehicleItem(parent.context)
                .apply {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                }
    }

    override fun onBindViewHolder(holder: ViewWrapper<ItemView<Vehicle>>, position: Int) {
        val game = items[position]

        holder.view.apply {
            bind(game)
        }

        holder.view.setOnClickListener { onClickAction?.invoke(items[position]) }
    }
}
