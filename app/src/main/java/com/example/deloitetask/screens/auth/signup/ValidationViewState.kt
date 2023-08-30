package com.example.deloitetask.screens.auth.signup


sealed class ValidationViewState {
    object FullNameEmpty : ValidationViewState()
    object PhoneNumberEmpty : ValidationViewState()
    object EmailEmpty : ValidationViewState()
    object EmailAlreadyExist : ValidationViewState()
    object EmailNotMatch : ValidationViewState()
    object NationalIdEmpty : ValidationViewState()
    object DateOfBirthEmpty : ValidationViewState()
    object PasswordEmpty : ValidationViewState()
    object LoginNotValid : ValidationViewState()
}