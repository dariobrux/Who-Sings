package com.dariobrux.whosings.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dariobrux.whosings.data.database.model.UserEntity

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This class is the representation of the database.
 *
 */

@Database(
    exportSchema = false, version = 1, entities = [
        UserEntity::class,
    ]
)
//@TypeConverters(
//    WeatherInfoConverter::class,
//    CityConverter::class
//)
abstract class WhoSingsDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "who-sings.db"

        private var instance: WhoSingsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): WhoSingsDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, WhoSingsDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun whoSingsDAO(): WhoSingsDAO

}