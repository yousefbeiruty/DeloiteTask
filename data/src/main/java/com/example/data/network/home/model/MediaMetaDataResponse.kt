package com.example.data.network.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MediaMetaDataResponse(
    @SerializedName("format")
    val format: String? = "",

    @SerializedName("height")
    val height: Int? = 0,

    @SerializedName("url")
    val url: String? = "",

    @SerializedName("width")
    val width: Int? = 0
)