package com.dariobrux.whosings.data.database.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This is the user entity stored in the database.
 * I use [name] as primary key because I want only one user for the
 * same name.
 *
 */
@Entity(tableName = "user")
data class UserEntity(

    @PrimaryKey
    @NonNull
    var name: String = "",

    @NonNull
    var isLogged: Boolean = false,

    @NonNull
    var scores: MutableList<ScoreEntity> = mutableListOf()

) : Serializable
