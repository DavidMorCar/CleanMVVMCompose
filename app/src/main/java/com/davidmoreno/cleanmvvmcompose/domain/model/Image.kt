package com.davidmoreno.cleanmvvmcompose.domain.model

import com.google.gson.annotations.SerializedName

/** Image model */
data class Image(
    @SerializedName("label")
    var url: String,
)