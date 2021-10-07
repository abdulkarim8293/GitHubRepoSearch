package com.iamkamrul.githubreposearch.di.componenet

import android.app.Application
import com.iamkamrul.githubreposearch.di.deps.ApplicationDeps
import com.iamkamrul.githubreposearch.di.module.AppModule
import com.iamkamrul.githubreposearch.di.module.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class,AppModule::class])
interface AppComponent : ApplicationDeps{
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application):AppComponent
    }

}

