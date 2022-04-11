package com.davidmoreno.cleanmvvmcompose.domain.model.response

import com.davidmoreno.cleanmvvmcompose.domain.model.Image
import com.google.gson.annotations.SerializedName

/** Album model */
data class Album(
    @SerializedName("title")
    var title: LabelResponse,
    @SerializedName("im:price")
    var price: LabelResponse,
    @SerializedName("id")
    var url: LabelResponse,
    @SerializedName("rights")
    var right: LabelResponse,
    @SerializedName("im:image")
    var imageList: List<Image>
)