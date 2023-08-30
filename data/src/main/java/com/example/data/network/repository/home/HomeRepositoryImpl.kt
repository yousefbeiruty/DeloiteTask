package com.example.data.network.repository.home

import com.example.data.network.extensions.toMostPopular
import com.example.data.network.extensions.tryRequest
import com.example.data.network.home.HomeService
import com.example.domain.common.ResultWrapper
import com.example.domain.model.home.MostPopular
import com.example.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeService: HomeService) :
    HomeRepository {
    override fun getMostPopulars(key: String):Flow<ResultWrapper<List<MostPopular>?>> = flow {
        val result = tryRequest(
            request = {
                homeService.getMostPopular(key)
            },
            dataToDomain = { response ->
                response?.resultResponses?.toMostPopular()
            }
        )
         emit(result)
    }
}