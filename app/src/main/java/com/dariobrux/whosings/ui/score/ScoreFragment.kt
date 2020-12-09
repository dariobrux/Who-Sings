package com.dariobrux.whosings.ui.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.whosings.R
import com.dariobrux.whosings.common.extension.getDimen
import com.dariobrux.whosings.common.extension.toGone
import com.dariobrux.whosings.common.extension.toScoreDataList
import com.dariobrux.whosings.common.extension.toVisible
import com.dariobrux.whosings.databinding.FragmentScoreBinding
import com.dariobrux.whosings.ui.utils.ScoreItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This Fragment shows the scores screen.
 *
 */
@AndroidEntryPoint
class ScoreFragment : Fragment() {

    private val viewModel: ScoreViewModel by viewModels()

    private var binding: FragmentScoreBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.let {
            it.recyclerScore.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            it.recyclerScore.addItemDecoration(ScoreItemDecoration(requireContext().getDimen(R.dimen.regular_space)))
        }

        viewModel.getUsers().observe(viewLifecycleOwner) {
            if (it.data.isNullOrEmpty()) {
                binding?.let { binding ->
                    binding.txtNoUsers.toVisible()
                    binding.recyclerScore.toGone()
                }
            } else {
                binding?.let { binding ->
                    binding.txtNoUsers.toGone()
                    binding.recyclerScore.toVisible()
                    binding.recyclerScore.adapter = ScoreAdapter(requireContext(), it.data.toScoreDataList(requireContext()))
                }
            }
        }
    }

    override fun onDestroyView() {
        binding?.recyclerScore?.adapter = null
        binding = null
        super.onDestroyView()
    }
}