package com.dariobrux.whosings.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.whosings.R
import com.dariobrux.whosings.data.database.model.UserEntity
import com.dariobrux.whosings.databinding.FragmentResultBinding
import com.dariobrux.whosings.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 * This Fragment shows the result of the game.
 *
 */
@AndroidEntryPoint
class ResultFragment : Fragment(), View.OnClickListener {

    /**
     * The ViewModel that handles all this Fragment logic.
     */
    private val viewModel: ResultViewModel by viewModels()

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentResultBinding? = null

    /**
     * The user retrieved by bundle arguments.
     */
    private lateinit var user: UserEntity

    /**
     * The score of the current game retrieved by arguments.
     */
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = requireArguments().getSerializable("user") as UserEntity
        score = requireArguments().getInt("score")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            txtLose.text = getString(R.string.lost_format, user.name)
            txtScore.text = getString(R.string.score_format, score)
            txtRecord.text = getString(R.string.record_format, user.scoreRecord)
            cardLogout.setOnClickListener(this@ResultFragment)
            cardRestart.setOnClickListener(this@ResultFragment)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onClick(v: View) {
        when (v) {
            binding?.cardRestart -> {
                NavHostFragment.findNavController(requireParentFragment()).navigate(
                    R.id.action_resultFragment_to_gameFragment,
                    Bundle().apply {
                        putSerializable("user", user)
                    },
                    NavOptions.Builder().setPopUpTo(R.id.resultFragment, true).build()
                )
            }
            binding?.cardLogout -> {
                viewModel.logout(user)
                NavHostFragment.findNavController(requireParentFragment()).navigate(
                    R.id.action_resultFragment_to_loginFragment,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.resultFragment, true).build()
                )
            }
        }
    }

}