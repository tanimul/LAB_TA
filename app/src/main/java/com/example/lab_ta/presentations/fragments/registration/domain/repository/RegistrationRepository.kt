package com.example.lab_ta.presentations.fragments.registration.domain.repository

import com.example.lab_ta.common.domain.model.User

interface RegistrationRepository {

    suspend fun registration(user: User)

}