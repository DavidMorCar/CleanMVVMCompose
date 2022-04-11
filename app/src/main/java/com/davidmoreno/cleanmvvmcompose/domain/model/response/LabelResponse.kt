package com.davidmoreno.cleanmvvmcompose.domain.model.response

import com.google.gson.annotations.SerializedName

/** Generic label response model */
data class LabelResponse(
    @SerializedName("label")
    var label: String,
)