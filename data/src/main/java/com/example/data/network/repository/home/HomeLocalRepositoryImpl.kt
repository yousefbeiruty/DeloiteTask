package com.example.data.network.repository.home

import com.example.data.cashe.manager.CachingManager
import com.example.data.cashe.manager.ProviderEnum
import com.example.data.cashe.utils.ResultWrapper
import com.example.data.cashe.utils.tryMapperQuery
import com.example.data.network.common.toMostPopular
import com.example.data.network.common.toMostPopularEntity
import com.example.domain.model.home.MostPopular
import com.example.domain.repository.home.HomeLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeLocalRepositoryImpl (private val cachingManager: CachingManager) :
    HomeLocalRepository {

    override fun getMostPopulars(): Flow<com.example.domain.common.ResultWrapper<List<MostPopular>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getMostPopulars()
        })
        { mostPopularEntity ->
            mostPopularEntity?.toMostPopular()
        }
        emit(result)
    }
    override fun getFilteredMostPopulars(searchQuery: String): Flow<com.example.domain.common.ResultWrapper<List<MostPopular>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getFilteredMostPopulars(searchQuery)
        })
        { mostPopularEntity ->
            mostPopularEntity?.toMostPopular()
        }
        emit(result)
    }

    override fun insertMostPopulars(mostPopulars: List<MostPopular>?): Flow<com.example.domain.common.ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                mostPopulars?.toMostPopularEntity()?.let {
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertMostPopulars(it)
                }
            }) {}
            emit(result)
        }


}
