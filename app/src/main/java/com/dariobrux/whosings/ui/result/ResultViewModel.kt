package com.dariobrux.whosings.ui.result

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dariobrux.whosings.data.database.model.UserEntity
import com.dariobrux.whosings.data.repository.GameRepository

/**
 *
 * Created by Dario Bruzzese on 11/12/2020.
 *
 * This is the ViewModel that contains all the logic and correlations between
 * Fragment and Repository.
 *
 */
class ResultViewModel @ViewModelInject constructor(private val repository: GameRepository) : ViewModel() {

    /**
     * Logout the user.
     * @param user the [UserEntity] to logout.
     */
    fun logout(user: UserEntity) {
        user.isLogged = false
        repository.updateUser(user)
    }
}