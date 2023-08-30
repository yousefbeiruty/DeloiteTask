package com.example.deloitetask.screens.auth.login

import android.util.Patterns
import com.example.data.cashe.manager.CachingManager
import com.example.data.cashe.manager.ProviderEnum
import com.example.deloitetask.common.BaseViewModel
import com.example.deloitetask.common.BaseViewState
import com.example.deloitetask.screens.auth.signup.ValidationViewState
import com.example.domain.common.ResultWrapper
import com.example.domain.common.UseCaseConstants
import com.example.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val cachingManager: CachingManager
) : BaseViewModel() {

    private var _loginSuccess: MutableSharedFlow<Boolean?> =
        MutableSharedFlow(replay = 1)
    val loginSuccess: SharedFlow<Boolean?> =
        _loginSuccess.asSharedFlow()

    private var _validationError: MutableSharedFlow<ValidationViewState> =
        MutableSharedFlow(replay = 1)
    val validationError: SharedFlow<ValidationViewState> =
        _validationError.asSharedFlow()

    fun doValidationTheLogin(
        email: String,
        password: String
    ) {
        launchCoroutine(coroutineExceptionHandler) {
            when {

                email.isEmpty() -> {
                    _validationError.emit(ValidationViewState.EmailEmpty)
                }
                ! Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    _validationError.emit(ValidationViewState.EmailNotMatch)
                }
                password.isEmpty() -> {
                    _validationError.emit(ValidationViewState.PasswordEmpty)
                }

                else -> {
                    doLogin(
                        email, password
                    )
                }
            }
        }
    }

    fun doLogin(
        email: String, password: String
    ) {
        launchCoroutine(coroutineExceptionHandler) {
            val params = HashMap<String, String>()
            params[UseCaseConstants.EMAIL] = email
            params[UseCaseConstants.PASSWORD] = password
            loginUseCase(params)
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }
                .onCompletion {
                    _state.emit(BaseViewState.DataLoaded)
                }
                .collect { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            if (result.data != null) {
                                cachingManager.getProvider(ProviderEnum.PREFERENCES)
                                    .setLoggedInUserEmail(result.data?.email)
                                _loginSuccess.emit(true)
                            } else {
                                _validationError.emit(ValidationViewState.LoginNotValid)
                            }
                        }
                        is ResultWrapper.Error -> {
                            _state.emit(
                                BaseViewState.Error(
                                    result.error
                                )
                            )
                        }
                    }
                }
        }
    }
}