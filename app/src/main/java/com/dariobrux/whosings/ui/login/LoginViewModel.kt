package com.dariobrux.whosings.ui.login

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariobrux.whosings.data.local.model.UserEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This is the ViewModel that contains all the logic and correlations between
 * Fragment and Repository.
 *
 */
class LoginViewModel @ViewModelInject constructor(private val repository: LoginRepository) : ViewModel() {

    val filledUser: MutableLiveData<UserEntity> = MutableLiveData(UserEntity())

    /**
     * Get the logged user
     */
    @ExperimentalCoroutinesApi
    fun getLoggedUser() = liveData {
        repository.getLoggedUser().collect {
            emit(it)
        }
    }

    /**
     * Bind the user name EditText observing its changing text.
     * Emit the user.
     * @param editText the EditText for the user name.
     */
    fun bind(editText: EditText) {
        editText.doOnTextChanged { text, _, _, _ ->
            CoroutineScope(Dispatchers.Main).launch {
                filledUser.value = UserEntity(text.toString(), false)
            }
        }
    }

    /**
     * Insert the user in the database.
     */
    @ExperimentalCoroutinesApi
    fun insertUser() = runBlocking {
        filledUser.value?.let {
            it.isLogged = true
            repository.insertUser(it).collect { result ->
                if (result) {
                    filledUser.value = it
                }
            }
        }
    }
}