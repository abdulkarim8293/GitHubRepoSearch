package com.iamkamrul.githubreposearch.di.deps

import android.app.Application
import android.content.Context
import com.iamkamrul.githubreposearch.api.GithubService


interface ApplicationDeps{
    fun provideApplication():Application
    fun provideGitHubService(): GithubService
}

fun Context.applicationDeps():ApplicationDeps{
    return (applicationContext as? HasApplicationDeps)?.getApplicationDeps()
        ?: throw IllegalAccessException("Application must have ApplicationDeps")
}