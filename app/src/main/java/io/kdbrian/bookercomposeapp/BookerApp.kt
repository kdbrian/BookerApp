package io.kdbrian.bookercomposeapp

import android.app.Application
import timber.log.Timber

class BookerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //debug logging
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}