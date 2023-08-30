package com.example.domain.usecase.home

import com.example.domain.common.BaseUseCase
import com.example.domain.common.ResultWrapper
import com.example.domain.extensions.networkBoundResource
import com.example.domain.extensions.resultWrapperData
import com.example.domain.model.home.MostPopular
import com.example.domain.repository.home.HomeLocalRepository
import com.example.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val localRepository: HomeLocalRepository
):
    BaseUseCase<String, Flow<ResultWrapper<List<MostPopular>?>>> {
    private var response: Flow<ResultWrapper<List<MostPopular>?>> = emptyFlow()
    override suspend fun invoke(params: String?):Flow<ResultWrapper<List<MostPopular>?>> =
        networkBoundResource(
            queryDb = {
                localRepository.getMostPopulars()
            },
            fetchApi = {
                homeRepository.getMostPopulars(key = params ?: "")
            },
            saveApiResult = { fetchResult ->
                fetchResult.collect { resultWrapper ->
                    this.response = flowOf(resultWrapper)

                    resultWrapperData(resultWrapper, { mostPopulars ->
                        localRepository.insertMostPopulars(
                            mostPopulars = mostPopulars
                        ).collect()
                    }, {
                        localRepository.getMostPopulars()
                    })
                }
            }, onQueryDbError = {
                response
            }
        )
}