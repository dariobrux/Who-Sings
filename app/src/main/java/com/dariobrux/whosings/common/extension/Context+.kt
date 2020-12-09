package com.dariobrux.whosings.common.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat

/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This file contains all the Activity related extended methods.
 *
 */

/**
 * Get the color from context.
 * @param colorRes the color resource to get.
 */
fun Context.getColor(@ColorRes colorRes: Int) : Int {
    return ContextCompat.getColor(this, colorRes)
}

/**
 * Get the dimen from context.
 * @param dimenRes the dimen resource to get.
 */
fun Context.getDimen(@DimenRes dimenRes: Int) : Int {
    return this.resources.getDimensionPixelSize(dimenRes)
}