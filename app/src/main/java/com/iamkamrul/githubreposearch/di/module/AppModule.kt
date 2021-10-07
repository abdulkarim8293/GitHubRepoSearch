package com.iamkamrul.githubreposearch.di.module

import com.iamkamrul.githubreposearch.data.GithubService
import com.iamkamrul.githubreposearch.data.RepoSearchRepoImpl
import com.iamkamrul.githubreposearch.domain.repo.RepoSearchRepository
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun provideRepoSearchRepo(service: GithubService):RepoSearchRepository{
        return RepoSearchRepoImpl(
            service
        )
    }
}