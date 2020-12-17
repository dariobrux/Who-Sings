package com.dariobrux.whosings.common.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
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

/**
 * Hide keyboard programmatically.
 */
fun Activity.hideKeyboard() {
    val imm: InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}