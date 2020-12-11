package com.dariobrux.whosings.ui.score

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariobrux.whosings.data.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 * This is the ViewModel that contains all the logic and correlations between
 * Fragment and Repository.
 *
 */
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