package com.iamkamrul.githubreposearch.di.deps

import android.app.Application
import android.content.Context
import com.iamkamrul.githubreposearch.data.GithubService
import com.iamkamrul.githubreposearch.domain.repo.RepoSearchRepository


interface ApplicationDeps{
    fun provideApplication():Application
    fun provideGitHubService(): GithubService
    fun provideRepoSearchRepo(): RepoSearchRepository
}

fun Context.applicationDeps():ApplicationDeps{
    return (applicationContext as? HasApplicationDeps)?.getApplicationDeps()
        ?: throw IllegalAccessException("Application must have ApplicationDeps")
}