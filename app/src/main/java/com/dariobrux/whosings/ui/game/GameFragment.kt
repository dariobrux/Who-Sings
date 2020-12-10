package com.dariobrux.whosings.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dariobrux.whosings.R
import com.dariobrux.whosings.data.database.model.UserEntity
import com.dariobrux.whosings.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This Fragment shows the game screen.
 *
 */
@AndroidEntryPoint
class GameFragment : Fragment() {

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentGameBinding? = null

    /**
     * The ViewModel that handles all this Fragment logic.
     */
    private val viewModel: GameViewModel by viewModels()

    private lateinit var user: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = requireArguments().getSerializable("user") as UserEntity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            txtName.text = getString(R.string.last_score_format, user.name, user.score)
        }

        viewModel.getChartArtists().observe(viewLifecycleOwner) {
            false
        }
    }
}