package com.vsantander.vehicleschallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class VehicleTO (
        @SerializedName("id")
        val id: String,

        @SerializedName("coordinate")
        val coordinate: CoordinateTO,

        @SerializedName("fleetType")
        val type: String,

        @SerializedName("heading")
        val heading: Float
)