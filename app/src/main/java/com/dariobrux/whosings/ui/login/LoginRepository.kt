package com.dariobrux.whosings.ui.login

import com.dariobrux.whosings.common.Resource
import com.dariobrux.whosings.data.local.WhoSingsDAO
import com.dariobrux.whosings.data.local.model.User
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
class LoginRepository @Inject constructor(private val dao: WhoSingsDAO) {

    /**
     * Get the logged user.
     * @return the logged user.
     */
    @ExperimentalCoroutinesApi
    suspend fun getLoggedUser() = flow {

        var result = Resource<User>(Resource.Status.LOADING, null, null)

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

}