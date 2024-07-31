package com.example.lab_ta.presentations.fragments.registration.domain.usecase

import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.presentations.fragments.registration.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val registrationRepository: RegistrationRepository) {
    suspend operator fun invoke(user: User) {
        registrationRepository.registration(user)
    }
}