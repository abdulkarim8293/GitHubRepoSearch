package com.iamkamrul.githubreposearch

import android.app.Application
import com.iamkamrul.githubreposearch.di.componenet.AppComponent
import com.iamkamrul.githubreposearch.di.componenet.DaggerAppComponent

class GitHubRepoApp : Application(){

    private val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun appComponent():AppComponent = appComponent
}