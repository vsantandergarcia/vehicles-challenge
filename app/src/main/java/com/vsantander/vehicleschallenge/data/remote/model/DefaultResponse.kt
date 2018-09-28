package com.vsantander.vehicleschallenge.data.remote.model

import com.google.gson.annotations.SerializedName

class DefaultResponse<T> (
        @SerializedName("poiList")
        var data: T
)