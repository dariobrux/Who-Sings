package com.dariobrux.whosings.common.extension

import android.content.Context
import androidx.annotation.DimenRes


/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This file contains all the Activity related extended methods.
 *
 */

/**
 * Get the dimen from context.
 * @param dimenRes the dimen resource to get.
 */
fun Context.getDimen(@DimenRes dimenRes: Int): Int {
    return this.resources.getDimensionPixelSize(dimenRes)
}