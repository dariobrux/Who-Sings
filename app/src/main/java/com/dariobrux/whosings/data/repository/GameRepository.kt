package com.dariobrux.whosings.data.repository

import com.dariobrux.whosings.common.Constants
import com.dariobrux.whosings.common.Resource
import com.dariobrux.whosings.common.extension.toArtistList
import com.dariobrux.whosings.common.extension.toRandomTrack
import com.dariobrux.whosings.common.extension.toSnippetLyrics
import com.dariobrux.whosings.data.local.game.Artist
import com.dariobrux.whosings.data.local.game.Snippet
import com.dariobrux.whosings.data.local.game.Track
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
     * @return the list of [Artist] mapped into a [Resource] object.
     */
    @ExperimentalCoroutinesApi
    suspend fun getChartArtists(page: Int) = flow {

        var result: Resource<List<Artist>> = Resource.loading(null)
        emit(result)

        kotlin.runCatching {

            api.getChartTracks("top", page, 3, "en", 1, Constants.API_KEY)

        }.onFailure {

            Timber.d("An exception occurred while retrieving chart artists: $it")
            result = Resource.error("An exception occurred while retrieving chart artists")

        }.onSuccess {

            result = if (it.isSuccess()) {
                Timber.d("Retrieved chart artists: ${it.data}")
                Resource.success(it.data!!.toArtistList())
            } else {
                Timber.d("An exception occurred while retrieving chart artists: $it")
                Resource.error("An exception occurred while retrieving chart artists")
            }
        }

        emit(result)
    }.flowOn(Dispatchers.IO)

    /**
     * Get the snippet lyrics.
     * @return the [Track] containing the snippet lyrics, mapped into a [Resource] object.
     */
    @ExperimentalCoroutinesApi
    suspend fun getSnippetLyrics(trackId: Long) = flow {

        var result: Resource<Snippet> = Resource.loading(null)
        emit(result)

        kotlin.runCatching {

            api.getSnippetLyrics(trackId, Constants.API_KEY)

        }.onFailure {

            Timber.d("An exception occurred while retrieving snippet lyrics: $it")
            result = Resource.error("An exception occurred while retrieving snippet lyrics")

        }.onSuccess {

            result = if (it.isSuccess()) {
                Timber.d("Retrieved snippet lyrics: ${it.data}")

                val snippet = it.data!!.toSnippetLyrics()

                if (snippet.text.isEmpty()) {
                    Resource.error("An exception occurred while retrieving snippet lyrics")
                } else {
                    Resource.success(snippet)
                }

            } else {
                Timber.d("An exception occurred while retrieving snippet lyrics: $it")
                Resource.error("An exception occurred while retrieving snippet lyrics")
            }
        }

        emit(result)

    }.flowOn(Dispatchers.IO)
}