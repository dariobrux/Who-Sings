package com.dariobrux.whosings.ui.common.extension

import android.app.Activity
import com.dariobrux.whosings.ui.MainActivity

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This file contains all the Activity related extended methods.
 *
 */

/**
 * Cast if possible the Activity to MainActivity.
 * @return the [MainActivity].
 */
fun Activity.toMainActivity(): MainActivity? {
    return this as? MainActivity
}