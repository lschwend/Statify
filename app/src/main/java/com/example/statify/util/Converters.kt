package com.example.statify.util

import androidx.room.TypeConverter
import com.example.statify.data.model.Artists
import com.example.statify.data.model.Images
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<Artists>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<Artists>::class.java).toList()

    @TypeConverter
    fun listToJsonStringImages(value: List<Images>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToListImages(value: String) = Gson().fromJson(value, Array<Images>::class.java).toList()
}