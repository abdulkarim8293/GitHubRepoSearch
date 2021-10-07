

package com.iamkamrul.githubreposearch.domain.entity

sealed class RepoSearchResult {
    data class Loading(val loading: Boolean) : RepoSearchResult()
    data class Success(val data: List<Repo>) : RepoSearchResult()
    data class Error(val error: String) : RepoSearchResult()

}
