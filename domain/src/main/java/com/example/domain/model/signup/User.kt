package com.example.domain.model.signup

data class User(
    var fullName: String,
    var email: String,
    var nationalId: String,
    var phoneNumber: String,
    var dateOfBirth: String,
    var password: String
)