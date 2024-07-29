package com.example.lab_ta.db.room.converter

import androidx.room.TypeConverter
import com.example.lab_ta.utils.extention.fromJson
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun fromStringArrayList(value: ArrayList<String>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): ArrayList<String> {
        return try {
            Gson().fromJson<ArrayList<String>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }

}