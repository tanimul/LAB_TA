package com.example.lab_ta.presentations.fragments.login.data

import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.db.room.MyRoomDatabase
import com.example.lab_ta.presentations.fragments.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val roomDatabase: MyRoomDatabase) :
    LoginRepository {

    override suspend fun userInfo(userName: String): Flow<User> {
       return roomDatabase.userDao().getUser(userName)
    }

}