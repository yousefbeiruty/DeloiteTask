package com.example.domain.repository.home

import com.example.domain.common.ResultWrapper
import com.example.domain.model.home.MostPopular
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getMostPopulars(key:String): Flow<ResultWrapper<List<MostPopular>?>>
}