package com.dariobrux.whosings.data.repository

import com.dariobrux.whosings.common.Resource
import com.dariobrux.whosings.data.database.WhoSingsDAO
import com.dariobrux.whosings.data.database.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This class is the repository that handles the communication
 * between the ViewModel and the Database.
 *
 */
class Repository @Inject constructor(private val dao: WhoSingsDAO) {

    /**
     * Get the logged user.
     * @return the logged user.
     */
    @ExperimentalCoroutinesApi
    suspend fun getLoggedUser() = flow {

        var result = Resource<UserEntity>(Resource.Status.LOADING, null, null)
        emit(result)

        kotlin.runCatching {
            dao.getLoggedUser()
        }.onFailure {
            Timber.d("An exception occurred: $it")
            result = Resource.error("An exception occurred")
        }.onSuccess {
            result = Resource.success(it)
        }

        emit(result)
    }.flowOn(Dispatchers.IO)

    /**
     * Insert the user in database.
     * @param user the user to insert.
     */
    @ExperimentalCoroutinesApi
    suspend fun insertUser(user: UserEntity) = flow {

        kotlin.runCatching {
            val dbUser = dao.getUser(user.name)?.apply {
                isLogged = true
            } ?: user
            dao.insertUser(dbUser)
        }.onSuccess {
            emit(true)
        }.onFailure {
            emit(false)
        }
    }.flowOn(Dispatchers.IO)

    /**
     * Get the list of all the stored users.
     */
    @ExperimentalCoroutinesApi
    suspend fun getUsers() = flow {

        var result: Resource<List<UserEntity>> = Resource.loading(emptyList())

        kotlin.runCatching {
            dao.getUsers()
        }.onSuccess {
            result = Resource.success(it ?: emptyList())
        }.onFailure {
            result = Resource.success(emptyList())
        }

        emit(result)
    }.flowOn(Dispatchers.IO)
}