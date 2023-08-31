package com.example.data.cashe.room.common

import com.example.data.cashe.manager.BaseRepository
import com.example.data.cashe.room.features.home.entities.MostPopularEntity
import com.example.data.cashe.room.features.signup.entities.UserEntity
import com.example.domain.common.ResultWrapper
import com.example.data.cashe.utils.safeLocalDataCall
import javax.inject.Inject

class RoomManager @Inject constructor(private val databaseRoom: DatabaseRoom) : BaseRepository {

    override suspend fun insertUserData(userEntity: UserEntity): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.userDao().insert(userEntity) }

    override suspend fun getUserData(email: String) =
        safeLocalDataCall { databaseRoom.userDao().getUserDataEntity(email) }

    override suspend fun login(email: String, password: String) =
        safeLocalDataCall { databaseRoom.userDao().login(email, password) }

    override suspend fun insertMostPopulars(mostPopularEntity: List<MostPopularEntity>): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.mostPopularDao().insert(mostPopularEntity) }

    override suspend fun getMostPopulars() =
        safeLocalDataCall { databaseRoom.mostPopularDao().getMostPopular() }

    override suspend fun getFilteredMostPopulars(searchQuery:String) =
        safeLocalDataCall { databaseRoom.mostPopularDao().getFilteredMostPopular(searchQuery) }

}