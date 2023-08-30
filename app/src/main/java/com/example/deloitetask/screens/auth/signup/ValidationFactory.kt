package com.example.deloitetask.screens.auth.signup

import android.content.Context
import com.example.deloitetask.R
import com.example.deloitetask.extensions.showToast

object ValidationFactory {

    fun validate(validationError: ValidationViewState, context: Context) {
        context.apply {
            when (validationError) {
                is ValidationViewState.FullNameEmpty -> {
                    showToast(getString(R.string.error_validation_full_name_empty))
                }
                is ValidationViewState.EmailNotMatch -> {
                    showToast(getString(R.string.error_validation_email_pattern))
                }
                is ValidationViewState.EmailEmpty -> {
                    showToast(getString(R.string.error_validation_email_empty))
                }
                is ValidationViewState.PhoneNumberEmpty -> {
                    showToast(getString(R.string.error_validation_phone_number_empty))
                }
                is ValidationViewState.PasswordEmpty -> {
                    showToast(getString(R.string.error_validation_password_empty))
                }
                is ValidationViewState.DateOfBirthEmpty -> {
                    showToast(getString(R.string.error_validation_date_of_birth_empty))
                }
                is ValidationViewState.NationalIdEmpty -> {
                    showToast(getString(R.string.error_validation_national_id_empty))
                }
                is ValidationViewState.EmailAlreadyExist -> {
                    showToast(getString(R.string.error_validation_user_exist))
                }
                is ValidationViewState.LoginNotValid -> {
                    showToast(getString(R.string.error_validation_login_invalid))
                }

            }
        }
    }
}