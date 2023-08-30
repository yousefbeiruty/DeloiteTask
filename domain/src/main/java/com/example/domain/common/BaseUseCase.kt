package com.example.domain.common


import com.example.domain.model.home.MostPopular
import com.example.domain.model.signup.User
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter?=null): Flow<ResultWrapper<User?>>
}