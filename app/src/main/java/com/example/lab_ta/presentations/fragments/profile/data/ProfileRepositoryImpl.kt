package com.example.lab_ta.presentations.fragments.profile.data

import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.db.room.MyRoomDatabase
import com.example.lab_ta.presentations.fragments.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val roomDatabase: MyRoomDatabase) :
    ProfileRepository {
    override suspend fun userInfo(userName: String): Flow<User> {
        return roomDatabase.userDao().getUser(userName)
    }

}