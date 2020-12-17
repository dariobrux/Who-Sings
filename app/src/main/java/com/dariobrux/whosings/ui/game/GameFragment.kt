package com.dariobrux.whosings.ui.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.whosings.R
import com.dariobrux.whosings.common.Constants
import com.dariobrux.whosings.common.extension.getDimen
import com.dariobrux.whosings.common.extension.getMaxScore
import com.dariobrux.whosings.common.manager.ITimerManagerListener
import com.dariobrux.whosings.common.manager.TimerManager
import com.dariobrux.whosings.data.database.model.UserEntity
import com.dariobrux.whosings.data.local.game.Artist
import com.dariobrux.whosings.databinding.FragmentGameBinding
import com.dariobrux.whosings.ui.utils.ScoreItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This Fragment shows the game screen.
 *
 */
@AndroidEntryPoint
class GameFragment : Fragment(), GameAdapter.OnItemSelectedListener, View.OnClickListener, ITimerManagerListener {

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

    /**
     * The current score during this game.
     */
    private var score = 0

    /**
     * This is the countdown timer below the choices.
     */
    @Inject
    lateinit var timer: TimerManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        timer.init(this)
    }

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
            txtName.text = getString(R.string.your_record_format, user.name, user.scores.getMaxScore())
            recyclerChoice.let { recycler ->
                recycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                recycler.adapter = adapter
                recycler.addItemDecoration(ScoreItemDecoration(requireContext().getDimen(R.dimen.regular_space)))
            }
            cardLogout.setOnClickListener(this@GameFragment)
            progressTimer.max = Constants.TIMER_GAME.toInt()
        }

        viewModel.user = user

        getChartArtists()

        viewModel.snippetLyric.observe(viewLifecycleOwner) {
            binding?.txtLyrics?.text = it?.text ?: ""
        }

        viewModel.matchCorrectness.observe(viewLifecycleOwner) { isCorrect ->
            isCorrect ?: return@observe
            matchCorrectness(isCorrect)
        }

        viewModel.score.observe(viewLifecycleOwner) {
            score = it
            binding?.txtScore?.text = getString(R.string.score_format, it)
        }
    }

    /**
     * Show the next question if is correct.
     * Go to the result screen if isn't correct.
     * @param isCorrect true if is correct, false if isn't correct.
     */
    @ExperimentalCoroutinesApi
    private fun matchCorrectness(isCorrect: Boolean) {
        if (isCorrect) {
            viewModel.getChartArtists().removeObservers(viewLifecycleOwner)
            getChartArtists()
        } else {
            NavHostFragment.findNavController(requireParentFragment()).navigate(
                R.id.action_gameFragment_to_resultFragment,
                Bundle().apply {
                    putSerializable("user", user)
                    putInt("score", score)
                },
                NavOptions.Builder().setPopUpTo(R.id.gameFragment, true).build()
            )
        }
    }

    /**
     * Observe the chart artists to populate the layout.
     * When the call return a successful value, it displays the
     * list of the artists inside the RecyclerView.
     */
    @ExperimentalCoroutinesApi
    private fun getChartArtists() {
        viewModel.getChartArtists().observe(viewLifecycleOwner) {
            if (it.isSuccess()) {

                timer.start()

                adapter.apply {
                    items.clear()
                    items.addAll(it.data!!)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        binding?.recyclerChoice?.adapter = null
        binding = null
        super.onDestroyView()
    }

    /**
     * Invoked when an artist has been selected.
     * @param item the [Artist].
     */
    @ExperimentalCoroutinesApi
    override fun onArtistSelected(item: Artist) {
        timer.cancel()
        viewModel.selectArtist(item)
    }

    override fun onClick(v: View) {
        when (v) {
            binding?.cardLogout -> {
                viewModel.logout(user)
                NavHostFragment.findNavController(requireParentFragment()).navigate(
                    R.id.action_gameFragment_to_loginFragment,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.gameFragment, true).build()
                )
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    @ExperimentalCoroutinesApi
    override fun onResume() {
        super.onResume()
        if (timer.isFinished()){
            matchCorrectness(false)
        }
    }

    /**
     * Callback fired on regular interval.
     * @param millis The amount of time until finished.
     */
    override fun onTimerRun(millis: Long) {
        Timber.d("Timer: $millis")
        binding?.progressTimer?.progress = Constants.TIMER_GAME.toInt() - millis.toInt()
    }

    /**
     * Callback fired when the time is up.
     */
    @ExperimentalCoroutinesApi
    override fun onTimerFinish() {
        binding?.progressTimer?.progress = Constants.TIMER_GAME.toInt()
        matchCorrectness(false)
    }
}