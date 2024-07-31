package com.example.lab_ta.common.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Keep
@Entity(tableName = "user")
data class User(
    val name: String,
    val userName: String,
    val phoneNumber: String,
    val password: String,
    val addedAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
