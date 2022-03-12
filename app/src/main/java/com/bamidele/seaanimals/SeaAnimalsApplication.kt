package com.bamidele.seaanimals

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SeaAnimalsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}