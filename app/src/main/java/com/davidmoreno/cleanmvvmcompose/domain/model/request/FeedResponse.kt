package com.davidmoreno.cleanmvvmcompose.domain.model.request

import com.google.gson.annotations.SerializedName

/** Part of the Album response model */
data class FeedResponse(
    @SerializedName("entry")
    var albumList: List<Album>,
)
