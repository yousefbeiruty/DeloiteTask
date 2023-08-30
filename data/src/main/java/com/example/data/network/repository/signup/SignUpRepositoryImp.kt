package com.example.data.network.repository.signup

import com.example.data.cashe.manager.CachingManager
import com.example.data.cashe.manager.ProviderEnum
import com.example.data.cashe.utils.ResultWrapper
import com.example.data.cashe.utils.tryMapperQuery
import com.example.data.network.common.toUser
import com.example.data.network.common.toUserEntity
import com.example.domain.model.signup.User
import com.example.domain.repository.signup.SignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignUpRepositoryImp (private val cachingManager: CachingManager) :
    SignUpRepository {

    override fun getUserData(email: String): Flow<com.example.domain.common.ResultWrapper<User?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getUserData(email)
        })
        { userEntity ->
            userEntity?.toUser()
        }
        emit(result)
    }

    override fun insertUserData(user: User): Flow<com.example.domain.common.ResultWrapper<Unit?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM)
                .insertUserData(user.toUserEntity())
        }) {}
        emit(result)
    }
}