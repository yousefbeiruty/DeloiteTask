package com.example.deloitetask.di

import com.example.data.cashe.manager.CachingManager
import com.example.data.network.home.HomeService
import com.example.data.network.repository.home.HomeLocalRepositoryImpl
import com.example.data.network.repository.home.HomeRepositoryImpl
import com.example.data.network.repository.login.LoginRepositoryImp
import com.example.data.network.repository.signup.SignUpRepositoryImp
import com.example.domain.repository.home.HomeLocalRepository
import com.example.domain.repository.home.HomeRepository
import com.example.domain.repository.login.LoginRepository
import com.example.domain.repository.signup.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryImpModule {

    @Provides
    @ViewModelScoped
    fun getSignUpRepository(cachingManager: CachingManager): SignUpRepository {
        return SignUpRepositoryImp(cachingManager)
    }

    @Provides
    @ViewModelScoped
    fun getLoginRepository(cachingManager: CachingManager): LoginRepository {
        return LoginRepositoryImp(cachingManager)
    }

    @Provides
    @ViewModelScoped
    fun getMostPopularRepository(homeService: HomeService): HomeRepository {
        return HomeRepositoryImpl(homeService)
    }

    @Provides
    @ViewModelScoped
    fun getMostPopularLocalRepository(cachingManager: CachingManager): HomeLocalRepository {
        return HomeLocalRepositoryImpl(cachingManager)
    }

}