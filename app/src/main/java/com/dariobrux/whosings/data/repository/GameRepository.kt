package com.dariobrux.whosings.data.repository

import com.dariobrux.whosings.common.Constants
import com.dariobrux.whosings.common.Resource
import com.dariobrux.whosings.common.extension.toArtistList
import com.dariobrux.whosings.data.local.game.Artist
import com.dariobrux.whosings.data.remote.ApiHelper
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
class GameRepository @Inject constructor(private val api: ApiHelper) {

    /**
     * Get the chart artists.
     * @return the logged user.
     */
    @ExperimentalCoroutinesApi
    suspend fun getChartArtists(page: Int) = flow {

        var result = Resource<List<Artist>>(Resource.Status.LOADING, null, null)
        emit(result)

        kotlin.runCatching {
            api.getChartTracks("top", page, 3, "en", 1, Constants.API_KEY)
        }.onFailure {
            Timber.d("An exception occurred: $it")
            result = Resource.error("An exception occurred")
        }.onSuccess {
            result = if (it.status == Resource.Status.SUCCESS && it.data != null) {
                Timber.d("Retrieved data: ${it.data}")
                Resource.success(it.data.toArtistList())
            } else {
                Timber.d("An exception occurred: $it")
                Resource.error("An exception occurred")
            }
        }

        emit(result)
    }.flowOn(Dispatchers.IO)
}