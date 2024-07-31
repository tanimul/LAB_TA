package com.example.lab_ta.presentations.fragments.registration.data

import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.db.room.MyRoomDatabase
import com.example.lab_ta.presentations.fragments.registration.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(private val roomDatabase: MyRoomDatabase) :
    RegistrationRepository {
    override suspend fun registration(user: User) {
        roomDatabase.userDao().addUser(user)
    }

}