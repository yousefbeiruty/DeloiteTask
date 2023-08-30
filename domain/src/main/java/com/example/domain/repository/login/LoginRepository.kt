package com.example.domain.repository.login



import com.example.domain.common.ResultWrapper
import com.example.domain.model.signup.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun doLogin(email: String, password: String): Flow<ResultWrapper<User?>>
}