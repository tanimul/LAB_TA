package com.example.lab_ta.presentations.fragments.login.domain.usecase

import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.presentations.fragments.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(userId: String): Flow<User> {
        return loginRepository.userInfo(userId)
    }
}