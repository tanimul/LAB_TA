package com.example.lab_ta.utils.extention

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

fun Any.toJson(): String? {
    return Gson().toJson(this)
}