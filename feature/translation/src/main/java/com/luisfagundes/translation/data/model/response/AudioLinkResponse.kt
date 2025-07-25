package com.luisfagundes.translation.data.model.response

import com.google.gson.annotations.SerializedName

data class AudioLinkResponse(
    @SerializedName("url")
    val url: String,
    @SerializedName("type")
    val type: String? = null
)