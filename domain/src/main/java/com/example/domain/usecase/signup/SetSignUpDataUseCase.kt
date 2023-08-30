package com.example.domain.usecase.signup

import com.example.domain.common.BaseUseCase
import com.example.domain.common.ResultWrapper
import com.example.domain.model.signup.User
import com.example.domain.repository.signup.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetSignUpDataUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) : BaseUseCase<User, Flow<ResultWrapper<Unit?>>?> {

    override suspend fun invoke(params: User?): Flow<ResultWrapper<Unit?>>? =
        params?.let {
            signUpRepository.insertUserData(
                user = it
            )
        }
}