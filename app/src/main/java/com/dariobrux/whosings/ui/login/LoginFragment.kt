package com.dariobrux.whosings.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.whosings.R
import com.dariobrux.whosings.common.Resource
import com.dariobrux.whosings.common.extension.hideKeyboard
import com.dariobrux.whosings.common.extension.toGone
import com.dariobrux.whosings.common.extension.toMainActivity
import com.dariobrux.whosings.common.extension.toVisible
import com.dariobrux.whosings.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This Fragment shows the login screen.
 * If a user is already stored in database with [com.dariobrux.whosings.data.database.model.UserEntity.isLogged] true,
 * then goes directly into the [com.dariobrux.whosings.ui.game.GameFragment] screen.
 *
 */
@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentLoginBinding? = null

    /**
     * The ViewModel that handles all this Fragment logic.
     */
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().toMainActivity()?.setStatusBarColor(R.color.indigo_400)
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.let {
            viewModel.bind(it.editName)
            it.cardPlay.setOnClickListener(this)
            it.cardScore.setOnClickListener(this)
        }

        getLoggedUser()

        viewModel.filledUser.observe(viewLifecycleOwner) {
            if (it.name.isNotEmpty()) {
                binding?.cardPlay?.run {
                    isClickable = true
                    alpha = 1f
                }
            } else {
                binding?.cardPlay?.run {
                    isClickable = false
                    alpha = 0.5f
                }
            }
        }
    }

    /**
     * Observe if there is already a logged user.
     */
    @ExperimentalCoroutinesApi
    private fun getLoggedUser() {
        viewModel.getLoggedUser().observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.NONE -> {
                    hideLoading()
                }
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.SUCCESS -> {
                    if (it.data != null) {
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(1000L)
                            hideLoading()
                            NavHostFragment.findNavController(requireParentFragment()).navigate(
                                R.id.action_loginFragment_to_gameFragment,
                                Bundle().apply {
                                    putSerializable("user", it.data)
                                },
                                NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                            )
                        }
                    } else {
                        hideLoading()
                    }
                }
                Resource.Status.ERROR -> {
                    hideLoading()
                }
            }
        }
    }

    /**
     * Display the loading views.
     */
    private fun showLoading() {
        binding?.apply {
            mask.toVisible()
            loading.toVisible()
        }
    }

    /**
     * Hide the loading views.
     */
    private fun hideLoading() {
        binding?.apply {
            mask.toGone()
            loading.toGone()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @ExperimentalCoroutinesApi
    override fun onClick(v: View) {
        when (v) {
            binding?.cardPlay -> {
                requireActivity().hideKeyboard()
                viewModel.insertUser()
                viewModel.getLoggedUser().removeObservers(viewLifecycleOwner)
                getLoggedUser()
            }
            binding?.cardScore -> {
                NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_loginFragment_to_scoreFragment)
            }
        }
    }
}