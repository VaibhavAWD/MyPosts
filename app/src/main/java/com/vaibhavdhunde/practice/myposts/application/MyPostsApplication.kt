package com.vaibhavdhunde.practice.myposts.application

import android.app.Application
import com.vaibhavdhunde.practice.myposts.BuildConfig
import com.vaibhavdhunde.practice.myposts.api.NetworkInterceptor
import com.vaibhavdhunde.practice.myposts.api.PostsApi
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import timber.log.Timber

@Suppress("unused")
class MyPostsApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyPostsApplication))
        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { PostsApi(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}