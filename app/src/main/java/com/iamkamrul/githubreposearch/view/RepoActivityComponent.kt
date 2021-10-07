package com.iamkamrul.githubreposearch.view

import androidx.lifecycle.ViewModel
import com.iamkamrul.githubreposearch.di.deps.ApplicationDeps
import com.iamkamrul.githubreposearch.di.deps.applicationDeps
import com.iamkamrul.githubreposearch.di.key.ViewModelKey
import com.iamkamrul.githubreposearch.di.scope.ActivityScope
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Qualifier

@ActivityScope
@Component(
    dependencies = [ApplicationDeps::class],
    modules = [MainActivityModule::class,MainTextModule::class]
)
interface RepoActivityComponent {
    @Component.Factory
    interface Factory{
        fun create(applicationDeps: ApplicationDeps): RepoActivityComponent
    }

    fun inject(activity:RepoSearchActivity)
}

fun RepoSearchActivity.inject(){
    DaggerRepoActivityComponent.factory().create(applicationDeps()).inject(this)
}

@Module
interface MainActivityModule{
    @Binds
    @IntoMap
    @ViewModelKey(RepoSearchViewModel::class)
    fun bindRepoSearchViewModel(viewModel: RepoSearchViewModel): ViewModel
}


//--------qualifier understanding-------
@Module
object MainTextModule{
   @Provides
   @Kamrul
   fun provideKamrul():String = "Kamrul"

    @Provides
    @Imtiaz
    fun provideImtiaz():String = "Imtiaz"
}

@Qualifier
annotation class Kamrul

@Qualifier
annotation class Imtiaz