package com.dariobrux.whosings.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.whosings.R
import com.dariobrux.whosings.common.Resource
import com.dariobrux.whosings.common.extension.getDimen
import com.dariobrux.whosings.data.database.model.UserEntity
import com.dariobrux.whosings.data.local.game.Artist
import com.dariobrux.whosings.databinding.FragmentGameBinding
import com.dariobrux.whosings.ui.utils.ScoreItemDecoration
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
class GameFragment : Fragment(), GameAdapter.OnItemSelectedListener {

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentGameBinding? = null

    /**
     * The ViewModel that handles all this Fragment logic.
     */
    private val viewModel: GameViewModel by viewModels()

    /**
     * The user retrieved by bundle arguments.
     */
    private lateinit var user: UserEntity

    /**
     * The adapter of the choices.
     */
    private lateinit var adapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = requireArguments().getSerializable("user") as UserEntity
        adapter = GameAdapter(requireContext(), mutableListOf(), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            txtName.text = getString(R.string.last_score_format, user.name, user.score)
            recyclerChoice.let { recycler ->
                recycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                recycler.adapter = adapter
                recycler.addItemDecoration(ScoreItemDecoration(requireContext().getDimen(R.dimen.regular_space)))
            }
        }

        viewModel.getChartArtists().observe(viewLifecycleOwner) {
            if (it.status != Resource.Status.SUCCESS || it.data.isNullOrEmpty()) {
                return@observe
            }
            adapter.apply {
                items.clear()
                items.addAll(it.data)
                notifyDataSetChanged()
            }
        }

        viewModel.snippetLyric.observe(viewLifecycleOwner) {
            binding?.txtLyrics?.text = it
        }
    }

    override fun onDestroyView() {
        binding?.recyclerChoice?.adapter = null
        binding = null
        super.onDestroyView()
    }

    override fun onArtistSelected(item: Artist) {
        // TODO("Not yet implemented")
    }
}