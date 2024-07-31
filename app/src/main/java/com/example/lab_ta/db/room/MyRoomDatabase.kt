package com.example.lab_ta.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.db.room.converter.Converter
import com.example.lab_ta.db.room.dao.UserDao

@Database(
    entities = [
        User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: MyRoomDatabase? = null
        fun getInstance(context: Context): MyRoomDatabase {
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
            INSTANCE?.openHelper?.writableDatabase
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MyRoomDatabase::class.java, "lab_ta"
        )
            .addMigrations()
            .build()
    }

}