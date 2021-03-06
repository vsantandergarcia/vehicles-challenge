package com.vsantander.vehicleschallenge.ui.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Jorge Guerrero
 */
class ViewWrapper<out V : View>(val view : V) : RecyclerView.ViewHolder(view)