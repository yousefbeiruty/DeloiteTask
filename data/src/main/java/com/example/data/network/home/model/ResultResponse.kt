package com.example.data.network.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResultResponse(

    @SerializedName("abstract")
    val `abstract`: String? = "",

    @SerializedName("adx_keywords")
    val adxKeywords: String? = "",

    @SerializedName("asset_id")
    val assetId: Long? = 0,

    @SerializedName("byline")
    val byline: String? = "",

    @SerializedName("column")
    val column: Any? = Any(),

    @SerializedName("des_facet")
    val desFacet: List<String?>? = listOf(),

    @SerializedName("eta_id")
    val etaId: Int? = 0,

    @SerializedName("geo_facet")
    val geoFacet: List<String?>? = listOf(),

    @SerializedName("id")
    val id: Long? = 0,

    @SerializedName("media")
    val mediaResponses: List<MediaResponse?>? = listOf(),

    @SerializedName("nytdsection")
    val nytdsection: String? = "",

    @SerializedName("org_facet")
    val orgFacet: List<String?>? = listOf(),

    @SerializedName("per_facet")
    val perFacet: List<String?>? = listOf(),

    @SerializedName("published_date")
    val publishedDate: String? = "",

    @SerializedName("section")
    val section: String? = "",

    @SerializedName("source")
    val source: String? = "",

    @SerializedName("subsection")
    val subsection: String? = "",

    @SerializedName("title")
    val title: String? = "",

    @SerializedName("type")
    val type: String? = "",

    @SerializedName("updated")
    val updated: String? = "",

    @SerializedName("uri")
    val uri: String? = "",

    @SerializedName("url")
    val url: String? = ""

)