package com.dariobrux.whosings.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariobrux.whosings.data.database.model.UserEntity

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
     * @return the [UserEntity] or null.
     */
    @Query("Select * from user where isLogged = 1")
    fun getLoggedUser(): UserEntity

    /**
     * Get all the users.
     * @return the list of [UserEntity] or null.
     */
    @Query("Select * from user")
    fun getUsers(): List<UserEntity>?

    /**
     * Insert the user in the database.
     * @param user: the [UserEntity] to insert.
     * Use the replacing strategy to override each existing item.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)
//
//    /**
//     * Delete all from the database.
//     */
//    @Query("DELETE FROM weather")
//    fun deleteAll()
}