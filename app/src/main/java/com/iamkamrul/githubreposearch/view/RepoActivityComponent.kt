package com.iamkamrul.githubreposearch.view

import androidx.lifecycle.ViewModel
import com.iamkamrul.githubreposearch.di.componenet.AppComponent
import com.iamkamrul.githubreposearch.di.key.ViewModelKey
import com.iamkamrul.githubreposearch.di.scope.ActivityScope
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [MainActivityModule::class]
)
interface RepoActivityComponent {
    @Component.Factory
    interface Factory{
        fun create(appComponent: AppComponent): RepoActivityComponent
    }

    fun inject(activity:RepoSearchActivity)
}

@Module
interface MainActivityModule{
    @Binds
    @IntoMap
    @ViewModelKey(RepoSearchViewModel::class)
    fun bindRepoSearchViewModel(viewModel: RepoSearchViewModel): ViewModel
}