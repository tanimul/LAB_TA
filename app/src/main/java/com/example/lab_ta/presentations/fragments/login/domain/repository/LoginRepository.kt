package com.example.lab_ta.presentations.fragments.login.domain.repository

import com.example.lab_ta.common.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun userInfo(userName: String): Flow<User>

}