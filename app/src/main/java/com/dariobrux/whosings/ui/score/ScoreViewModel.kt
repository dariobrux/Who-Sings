package com.dariobrux.whosings.ui.score

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariobrux.whosings.data.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

class ScoreViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    /**
     * Get all the stored users.
     */
    @ExperimentalCoroutinesApi
    fun getUsers() = liveData {
        repository.getUsers().collect {
            emit(it)
        }
    }
}