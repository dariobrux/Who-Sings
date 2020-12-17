package com.dariobrux.whosings.data.database.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dariobrux.whosings.data.database.converter.DateConverter
import java.io.Serializable
import java.util.*

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This is the score entity stored in the database.
 * I use [date] as primary key because I want only one score at time.
 *
 */
@Entity(tableName = "score")
data class ScoreEntity(

    @PrimaryKey
    @NonNull
    var date: Date = Date(),

    @NonNull
    var score: Int = 0

) : Serializable
