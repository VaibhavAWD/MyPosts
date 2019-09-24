package com.vaibhavdhunde.practice.myposts.application

import android.app.Application
import com.vaibhavdhunde.practice.myposts.BuildConfig
import timber.log.Timber

@Suppress("unused")
class MyPostsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}