package com.example.domain.repository.signup

import com.example.domain.common.ResultWrapper
import com.example.domain.model.signup.User
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {
    fun getUserData(email:String): Flow<ResultWrapper<User?>>
    fun insertUserData(user: User): Flow<ResultWrapper<Unit?>>
}