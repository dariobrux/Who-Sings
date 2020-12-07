package com.dariobrux.whosings.data.local

import androidx.room.Dao
import androidx.room.Query
import com.dariobrux.whosings.data.local.model.User

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This interface is the DAO.
 * It's responsible for defining the methods that access the database.
 *
 */

@Dao
interface WhoSingsDAO {

    /**
     * Get the user logged.
     * @return the [User] or null.
     */
    @Query("Select * from user where isLogged = 1")
    fun getLoggedUser(): User

//    /**
//     * Insert the weather in the database.
//     * @param weatherEntity: the [WeatherEntity] to insert.
//     * Use the replacing strategy to override each existing item.
//     */
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertWeather(weatherEntity: WeatherEntity)
//
//    /**
//     * Delete all from the database.
//     */
//    @Query("DELETE FROM weather")
//    fun deleteAll()
}