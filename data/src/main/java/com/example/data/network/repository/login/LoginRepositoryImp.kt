package com.example.data.network.repository.login

import com.example.data.cashe.manager.CachingManager
import com.example.data.cashe.manager.ProviderEnum
import com.example.domain.common.ResultWrapper
import com.example.data.cashe.utils.tryMapperQuery
import com.example.data.network.common.toUser
import com.example.domain.model.signup.User
import com.example.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImp (private val cachingManager: CachingManager) :
    LoginRepository {

    override fun doLogin(email: String, password: String): Flow<ResultWrapper<User?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).login(email,password)
        })
        { userEntity ->
            userEntity?.toUser()
        }
        emit(result)
    }
}