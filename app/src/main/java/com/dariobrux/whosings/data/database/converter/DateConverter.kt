package com.dariobrux.whosings.data.database.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Dario Bruzzese on 17/122020.
 *
 * This class is the de/serializer of the ScoreEntity. It's used by Database to convert
 * object <-> string.
 */
object DateConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    @JvmStatic
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}