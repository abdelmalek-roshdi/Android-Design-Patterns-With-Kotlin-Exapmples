package com.example.movieappkotlin.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class IntegerListTypeConverter {
    @TypeConverter
    fun stringToIntegertList(data: String?): List<Int> {
        val gson = Gson()
        if (data != null) {
            if (data.length != 0 && data != "null") {
                val listType = object :
                    TypeToken<List<Int?>?>() {}.type
                return gson.fromJson(data, listType)
            }
            return ArrayList()
        }

        return return ArrayList()
    }

    @TypeConverter
    fun integertListToString(integers: List<Int?>?): String {
        val gson = Gson()
        return gson.toJson(integers)
    }
}