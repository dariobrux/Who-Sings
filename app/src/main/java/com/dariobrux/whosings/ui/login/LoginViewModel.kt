package com.dariobrux.whosings.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dariobrux.whosings.common.Resource
import com.dariobrux.whosings.data.local.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This is the ViewModel that contains all the logic and correlations between
 * Fragment and Repository.
 *
 */
class LoginViewModel @ViewModelInject constructor(private val repository: LoginRepository) : ViewModel() {

//    val loggedUser = MutableLiveData<Resource<User>>()

    /**
     * Get the logged user
     */
    @ExperimentalCoroutinesApi
    fun getLoggedUser() = liveData {
        repository.getLoggedUser().collect {
            emit(it)
        }
    }
//        viewModelScope.launch {
//
//        }
//    }
}