package com.example.domain.usecase.login

import com.example.domain.common.BaseUseCase
import com.example.domain.common.ResultWrapper
import com.example.domain.common.UseCaseConstants
import com.example.domain.model.signup.User
import com.example.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<Map<String, String>, Flow<ResultWrapper<User?>>?> {

    override suspend fun invoke(params: Map<String, String>?): Flow<ResultWrapper<User?>> =
        loginRepository.doLogin(
            email = params?.get(UseCaseConstants.EMAIL).toString(),
            password = params?.get(UseCaseConstants.PASSWORD).toString()
        )
}