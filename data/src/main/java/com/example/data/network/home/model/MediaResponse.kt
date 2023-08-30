package com.example.data.network.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MediaResponse(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int? = 0,

    @SerializedName("caption")
    val caption: String? = "",

    @SerializedName("copyright")
    val copyright: String? = "",

    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetaDataResponse?>? = listOf(),

    @SerializedName("subtype")
    val subtype: String? = "",

    @SerializedName("type")
    val type: String? = ""
)