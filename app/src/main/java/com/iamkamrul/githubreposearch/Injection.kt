

package com.iamkamrul.githubreposearch

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.iamkamrul.githubreposearch.api.GithubService
import com.iamkamrul.githubreposearch.view.RepoSearchRepository
import com.iamkamrul.githubreposearch.view.ViewModelFactory

object Injection {
    private fun provideGithubRepository(): RepoSearchRepository {
        return RepoSearchRepository(GithubService.create())
    }

    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ViewModelFactory(owner, provideGithubRepository())
    }
}
