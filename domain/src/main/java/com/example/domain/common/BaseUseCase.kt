package com.example.domain.common


import com.example.domain.model.home.MostPopular
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter?=null): Result
}