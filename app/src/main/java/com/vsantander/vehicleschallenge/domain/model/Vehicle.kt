package com.vsantander.vehicleschallenge.domain.model

import android.os.Parcelable
import com.vsantander.vehicleschallenge.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
        val id: String,
        val coordinate: Coordinate,
        val type: String
) : Parcelable {

    companion object {
        const val TYPE_TAXI = "TAXI"
    }

    val imageResource: Int
        get() {
            return if (type == TYPE_TAXI) {
                R.drawable.taxi
            } else {
                R.drawable.pooling
            }
        }
}

