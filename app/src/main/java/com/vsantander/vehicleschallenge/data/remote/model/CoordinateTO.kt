package com.vsantander.vehicleschallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class CoordinateTO (
        @SerializedName("latitude")
        val latitude: Double,

        @SerializedName("longitude")
        val longitude: Double
)