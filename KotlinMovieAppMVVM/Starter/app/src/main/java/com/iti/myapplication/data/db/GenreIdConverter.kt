package com.iti.myapplication.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class GenreIdConverter {
    @TypeConverter
    fun stringToGenreList(data: String?): MutableList<Int?>? {
        val gson = Gson()
        if (data != null) {
            if (data.length != 0 && data != "null") {
                val listType = object : TypeToken<MutableList<Int?>?>() {}.type
                return gson.fromJson(data, listType)
            }
        }
        return ArrayList()
    }

    @TypeConverter
    fun genreListToString(integers: MutableList<Int?>?): String? {
        val gson = Gson()
        return if (integers == null) {
            gson.toJson(ArrayList<Int?>())
        } else gson.toJson(integers)
    }
}