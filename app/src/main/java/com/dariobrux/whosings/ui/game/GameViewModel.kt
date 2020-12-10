package com.dariobrux.whosings.ui.game

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariobrux.whosings.data.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

class GameViewModel @ViewModelInject constructor(private val repository: GameRepository) : ViewModel() {

    /**
     * Get the chart artists.
     */
    @ExperimentalCoroutinesApi
    fun getChartArtists() = liveData {
        repository.getChartArtists(1).collect {
            emit(it)
        }
    }
}