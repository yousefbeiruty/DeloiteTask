package com.example.domain.usecase.signup

import com.example.domain.common.BaseUseCase
import com.example.domain.common.ResultWrapper
import com.example.domain.model.signup.User
import com.example.domain.repository.signup.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSignUpDataUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) : BaseUseCase<String, Flow<ResultWrapper<User?>>?> {

    override suspend fun invoke(params: String?): Flow<ResultWrapper<User?>>? =
        params?.let {
            signUpRepository.getUserData(
                email = params
            )
        }
}