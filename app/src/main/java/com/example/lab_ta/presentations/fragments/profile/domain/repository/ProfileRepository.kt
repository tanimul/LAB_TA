package com.example.lab_ta.presentations.fragments.profile.domain.repository

import com.example.lab_ta.common.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun userInfo(userName: String): Flow<User>

}