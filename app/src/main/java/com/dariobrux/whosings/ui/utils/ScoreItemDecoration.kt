package com.dariobrux.whosings.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * Created by Dario Bruzzese on 28/11/2020.
 *
 * This class is the ItemDecoration useful for the RecyclerView for Pokemon stats.
 */
class ScoreItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let {
            outRect.bottom = space
        }
    }
}