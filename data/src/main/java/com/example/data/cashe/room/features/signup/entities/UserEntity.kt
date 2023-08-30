package com.example.data.cashe.room.features.signup.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserEntity")
data class UserEntity(

    @SerializedName("fullName")
    var fullName: String,

    @SerializedName("email")
    @PrimaryKey
    var email: String,

    @SerializedName("nationalId")
    var nationalId: String,

    @SerializedName("phoneNumber")
    var phoneNumber: String,

    @SerializedName("dateOfBirth")
    var dateOfBirth: String,

    @SerializedName("password")
    var password: String

)
