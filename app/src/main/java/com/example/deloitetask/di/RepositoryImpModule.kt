package com.example.deloitetask.di

import com.example.data.cashe.manager.CachingManager
import com.example.data.network.home.HomeService
import com.example.data.network.movie.MovieService
import com.example.data.network.repository.home.HomeLocalRepositoryImpl
import com.example.data.network.repository.home.HomeRepositoryImpl
import com.example.data.network.repository.login.LoginRepositoryImp
import com.example.data.network.repository.movie.MovieRemoteDataRepositoryImpl
import com.example.data.network.repository.movie.MovieRepositoryImpl
import com.example.data.network.repository.signup.SignUpRepositoryImp
import com.example.domain.repository.home.HomeLocalRepository
import com.example.domain.repository.home.HomeRepository
import com.example.domain.repository.login.LoginRepository
import com.example.domain.repository.movie.MovieRemoteDataRepository
import com.example.domain.repository.movie.MovieRepository
import com.example.domain.repository.signup.SignUpRepository
import com.example.domain.usecase.movie.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

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

    @Provides
    @ViewModelScoped
    fun getMoviesRepository(movieService: MovieService): MovieRemoteDataRepository {
        return MovieRemoteDataRepositoryImpl(movieService)
    }
    @Provides
    @ViewModelScoped
    fun providesMovieRepository(movieRemoteDataSource: MovieRemoteDataRepository): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource)
    }

}