package com.example.deloitetask.screens.main.ui.more

sealed class MoreViewState {

    object NavigateToSettings :MoreViewState()
    object LogoutUser:MoreViewState()
}
