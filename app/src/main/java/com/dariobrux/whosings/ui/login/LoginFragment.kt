package com.dariobrux.whosings.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.whosings.R
import com.dariobrux.whosings.common.Resource
import com.dariobrux.whosings.common.extension.toMainActivity
import com.dariobrux.whosings.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This Fragment shows the login screen.
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
            it.cardScores.setOnClickListener(this)
        }

        viewModel.getLoggedUser().observe(viewLifecycleOwner) {
            if (it.status == Resource.Status.SUCCESS && it.data != null) {
                //todo c è l utente e vado alla prossima schermata
            }
        }

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
                viewModel.insertUser()
            }
            binding?.cardScores -> {
                NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_loginFragment_to_scoreFragment)
            }
        }
    }
}