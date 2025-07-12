package com.luisfagundes.translation.data.model.response

import com.google.gson.annotations.SerializedName

internal data class ApiUsageResponse(
    @SerializedName("character_count")
    val characterCount: Long,
    @SerializedName("character_limit")
    val characterLimit: Long
)