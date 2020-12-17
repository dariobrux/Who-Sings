package com.dariobrux.whosings.data.database.converter

import androidx.room.TypeConverter
import com.dariobrux.whosings.data.database.model.ScoreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Dario Bruzzese on 17/122020.
 *
 * This class is the de/serializer of the List of Score. It's used by Database to convert
 * list of object <-> string.
 */
object ScoreConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): MutableList<ScoreEntity> {
        val listType = object : TypeToken<MutableList<ScoreEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: MutableList<ScoreEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}