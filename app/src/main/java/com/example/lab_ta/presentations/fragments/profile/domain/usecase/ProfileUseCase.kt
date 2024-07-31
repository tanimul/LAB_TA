package com.example.lab_ta.presentations.fragments.profile.domain.usecase

import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.presentations.fragments.profile.domain.repository.ProfileRepository
import com.example.lab_ta.presentations.fragments.registration.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(userId: String): Flow<User> {
        return profileRepository.userInfo(userId)
    }
}