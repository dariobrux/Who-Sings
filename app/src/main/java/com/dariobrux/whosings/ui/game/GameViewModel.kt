package com.dariobrux.whosings.ui.game

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariobrux.whosings.common.extension.toRandomTrack
import com.dariobrux.whosings.data.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

class GameViewModel @ViewModelInject constructor(private val repository: GameRepository) : ViewModel() {

    val snippetLyric = MutableLiveData("")

    /**
     * Get the chart artists.
     */
    @ExperimentalCoroutinesApi
    fun getChartArtists() = liveData {
        repository.getChartArtists(1).collect {

            if (it.isSuccess()) {

                it.data!!.toRandomTrack()?.let { track ->

                    repository.getSnippetLyrics(track.id).collect { snippetResource ->

                        if (snippetResource.isSuccess()) {
                            snippetLyric.value = snippetResource.data!!.text
                        }
                    }
                }
            }

            emit(it)
        }
    }
}