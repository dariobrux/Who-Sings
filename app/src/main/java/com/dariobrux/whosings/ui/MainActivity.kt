package com.dariobrux.whosings.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.dariobrux.whosings.R
import com.dariobrux.whosings.common.extension.toGone
import com.dariobrux.whosings.common.extension.toVisible
import com.dariobrux.whosings.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This is the main activity, where the application starts its
 * navigation.
 *
 * It is annotated by AndroidEntryPoint to integrate Hilt in this
 * activity.
 *
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * View binder. Destroy it in onDestroy avoiding memory leaks.
     */
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

    }

    /**
     * Set the Status Bar color.
     * @param color the new color to apply. This must be a color resource.
     */
    fun setStatusBarColor(@ColorRes color: Int) {
        window.statusBarColor = ContextCompat.getColor(this, color)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}