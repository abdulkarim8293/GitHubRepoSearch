package com.iamkamrul.githubreposearch

import android.app.Application
import com.iamkamrul.githubreposearch.di.componenet.DaggerAppComponent
import com.iamkamrul.githubreposearch.di.deps.ApplicationDeps
import com.iamkamrul.githubreposearch.di.deps.HasApplicationDeps

class GitHubRepoApp : Application(),HasApplicationDeps{
    private val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun getApplicationDeps(): ApplicationDeps = appComponent
}