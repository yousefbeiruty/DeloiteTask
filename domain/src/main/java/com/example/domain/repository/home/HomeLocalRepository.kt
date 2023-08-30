package com.example.domain.repository.home

import com.example.domain.common.ResultWrapper
import com.example.domain.model.home.MostPopular
import kotlinx.coroutines.flow.Flow

interface HomeLocalRepository {
    fun getMostPopulars(): Flow<ResultWrapper<List<MostPopular>?>>
    fun insertMostPopulars(mostPopulars: List<MostPopular>?): Flow<ResultWrapper<Unit?>>
    fun getFilteredMostPopulars(searchQuery: String): Flow<ResultWrapper<List<MostPopular>?>>
}