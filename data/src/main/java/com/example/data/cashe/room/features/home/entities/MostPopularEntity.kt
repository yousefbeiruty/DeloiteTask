package com.example.data.cashe.room.features.home.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MostPopularEntity")
data class MostPopularEntity(

    @SerializedName("title")
    var title: String,

    @SerializedName("newsImage")
    var newsImage: String,

    @SerializedName("publishTime")
    var publishTime: String,

    @SerializedName("publishTime")
    var url: String,

    @SerializedName("id")
    @PrimaryKey
    var id: Long,

    )