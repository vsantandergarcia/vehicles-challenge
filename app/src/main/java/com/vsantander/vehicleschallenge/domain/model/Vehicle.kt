package com.vsantander.vehicleschallenge.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle (
        val id: String,
        val coordinate: Coordinate,
        val type: String,
        val heading: Float
) : Parcelable