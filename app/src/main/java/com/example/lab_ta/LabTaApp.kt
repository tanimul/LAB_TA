package com.example.lab_ta

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class LabTaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // initialize timber in application class
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}