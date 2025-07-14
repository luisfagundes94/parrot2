package com.luisfagundes.dictionary.data.model.response

import com.google.gson.annotations.SerializedName

data class ExampleResponse(
    @SerializedName("dst")
    val dst: String,
    @SerializedName("src")
    val src: String,
)