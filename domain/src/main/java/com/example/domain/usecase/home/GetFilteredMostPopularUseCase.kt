package com.example.domain.usecase.home

import com.example.domain.common.BaseUseCase
import com.example.domain.common.ResultWrapper
import com.example.domain.model.home.MostPopular
import com.example.domain.repository.home.HomeLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredMostPopularUseCase @Inject constructor(
    private val localRepository: HomeLocalRepository
) : BaseUseCase<String, Flow<ResultWrapper<List<MostPopular>?>>> {

    override suspend fun invoke(params: String?): Flow<ResultWrapper<List<MostPopular>?>> =
        localRepository.getFilteredMostPopulars(params ?: "")
}