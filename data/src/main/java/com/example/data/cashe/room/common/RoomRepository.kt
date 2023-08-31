package com.example.data.cashe.room.common

import com.example.data.cashe.room.features.home.entities.MostPopularEntity
import com.example.data.cashe.room.features.signup.entities.UserEntity
import com.example.domain.common.ResultWrapper

interface RoomRepository {

    suspend fun insertUserData(userEntity: UserEntity): ResultWrapper<Unit>? = null
    suspend fun getUserData(email: String): ResultWrapper<UserEntity>? = null
    suspend fun login(email: String,password: String): ResultWrapper<UserEntity>? = null

    suspend fun insertMostPopulars(mostPopularEntity: List<MostPopularEntity>): ResultWrapper<Unit>? = null
    suspend fun getMostPopulars(): ResultWrapper<List<MostPopularEntity>>? = null
    suspend fun getFilteredMostPopulars(searchQuery:String): ResultWrapper<List<MostPopularEntity>>? = null
}