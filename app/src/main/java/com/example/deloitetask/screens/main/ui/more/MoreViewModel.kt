package com.example.deloitetask.screens.main.ui.more

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.cashe.manager.CachingManager
import com.example.data.cashe.manager.ProviderEnum
import com.example.deloitetask.common.BaseViewModel
import com.example.deloitetask.common.BaseViewState
import com.example.domain.common.ResultWrapper
import com.example.domain.model.signup.User
import com.example.domain.usecase.signup.GetSignUpDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val getSignUpDataUseCase: GetSignUpDataUseCase,
    private val cachingManager: CachingManager
) : BaseViewModel() {

    private var _personalDetailsSuccess: MutableSharedFlow<User?> =
        MutableSharedFlow(replay = 1)
    val personalDetailsSuccess: SharedFlow<User?> =
        _personalDetailsSuccess.asSharedFlow()

    private var _viewState: MutableSharedFlow<MoreViewState> =
        MutableSharedFlow()
    val viewState: SharedFlow<MoreViewState> =
        _viewState.asSharedFlow()

    fun getUserDetails() {
        launchCoroutine(coroutineExceptionHandler) {
            getSignUpDataUseCase(
                cachingManager.getProvider(ProviderEnum.PREFERENCES).getLoggedInUserEmail().first()
            )
                ?.onStart {
                    _state.emit(BaseViewState.Loading)
                }
                ?.onCompletion {
                    _state.emit(BaseViewState.DataLoaded)
                }?.collect { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            _personalDetailsSuccess.emit(result.data)
                        }
                        is ResultWrapper.Error -> {
                            _state.emit(
                                BaseViewState.Error(
                                    result.error
                                )
                            )
                        }

                        else -> {}
                    }
                }
        }
    }

    fun doLogout() {
        launchCoroutine(coroutineExceptionHandler) {
            cachingManager.getProvider(ProviderEnum.PREFERENCES).setLoggedInUserEmail(null)
            _viewState.emit(MoreViewState.LogoutUser)
        }
    }

    fun navigateToSettings() {
        launchCoroutine(coroutineExceptionHandler) {
            _viewState.emit(MoreViewState.NavigateToSettings)
        }
    }


}