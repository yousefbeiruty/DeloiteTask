package com.example.deloitetask.screens.splash

sealed class SplashNavigationViewState {
    object NavigateToMain : SplashNavigationViewState()
    object NavigateToLogin : SplashNavigationViewState()
}