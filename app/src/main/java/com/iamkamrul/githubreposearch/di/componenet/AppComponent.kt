package com.iamkamrul.githubreposearch.di.componenet

import android.app.Application
import com.iamkamrul.githubreposearch.api.GithubService
import com.iamkamrul.githubreposearch.di.module.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent{
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application):AppComponent
    }
    fun provideApplication():Application
    fun provideGitHubService():GithubService
}

