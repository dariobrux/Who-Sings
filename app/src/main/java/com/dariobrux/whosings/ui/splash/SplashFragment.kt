package com.dariobrux.whosings.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.dariobrux.whosings.R
import com.dariobrux.whosings.databinding.FragmentSplashBinding
import com.dariobrux.whosings.common.extension.toMainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This is the splash fragment, the first fragment displayed.
 *
 */
@AndroidEntryPoint
class SplashFragment : Fragment() {

    /**
     * View binder. Destroy it in onDestroyView avoiding memory leaks.
     */
    private var binding: FragmentSplashBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().toMainActivity()?.setStatusBarColor(R.color.red_400)
    }

    override fun onResume() {
        super.onResume()
        with(binding!!.imgSplash) {
            scaleX = 0.2f
            scaleY = 0.2f
            alpha = 0f
            animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(2000).setInterpolator(OvershootInterpolator()).withEndAction {
                NavHostFragment.findNavController(requireParentFragment()).navigate(
                    R.id.action_splashFragment_to_loginFragment,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
                )
            }.start()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}