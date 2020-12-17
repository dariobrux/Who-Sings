package com.dariobrux.whosings.ui.game

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariobrux.whosings.common.extension.toRandomTrack
import com.dariobrux.whosings.data.database.model.ScoreEntity
import com.dariobrux.whosings.data.database.model.UserEntity
import com.dariobrux.whosings.data.local.game.Artist
import com.dariobrux.whosings.data.local.game.Snippet
import com.dariobrux.whosings.data.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlin.math.max

class GameViewModel @ViewModelInject constructor(private val repository: GameRepository) : ViewModel() {

    /**
     * Observable that contains the snippet of the current displayed lyrics.
     */
    val snippetLyric: MutableLiveData<Snippet?> = MutableLiveData(null)

    val matchCorrectness: MutableLiveData<Boolean> = MutableLiveData(null)

    /**
     * The current user that's playing.
     */
    lateinit var user: UserEntity

    /**
     * Observable that emit the score.
     */
    val score: MutableLiveData<Int> = MutableLiveData(0)

    private var chartPage = 1

    /**
     * Get the chart artists.
     */
    @ExperimentalCoroutinesApi
    fun getChartArtists() = liveData {
        repository.getChartArtists(chartPage).collect {

            if (it.isSuccess()) {

                it.data!!.toRandomTrack()?.let { track ->

                    repository.getSnippetLyrics(track.id).collect { snippetResource ->

                        if (snippetResource.isSuccess()) {
                            snippetLyric.value = snippetResource.data!!
                        }
                    }
                }
            }

            emit(it)
        }
    }

    /**
     * After having selected an artist, check if its track is the same
     * of the snippet lyrics. If are the same, increment the chart page
     * to retrieve other snippet and artists.
     * @param artist the [Artist] selected.
     */
    @ExperimentalCoroutinesApi
    fun selectArtist(artist: Artist) {
        artist.track ?: return
        snippetLyric.value ?: return

        matchCorrectness.value = if (artist.track!!.id == snippetLyric.value!!.trackId) {
            chartPage++
            score.value = score.value!! + 1
            true
        } else {
            user.scores.add(ScoreEntity(score = score.value!!))
            repository.updateUser(user)
            false
        }
    }
}