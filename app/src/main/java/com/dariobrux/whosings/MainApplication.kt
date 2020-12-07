package com.dariobrux.whosings

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This is the application class declared in Manifest.
 * It's annotated by Hilt.
 */

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}
