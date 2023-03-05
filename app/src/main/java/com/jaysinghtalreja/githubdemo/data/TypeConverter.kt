package com.jaysinghtalreja.githubdemo.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromStringToList(value: String?): List<String?>? {
        val listType =
            object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    @JvmStatic
    fun fromListToString(list: List<String?>?): String? { //need to use this
        val gson = Gson()
        return gson.toJson(list)
    }
}