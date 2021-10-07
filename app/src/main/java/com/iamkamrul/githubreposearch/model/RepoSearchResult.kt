

package com.iamkamrul.githubreposearch.model

import com.iamkamrul.githubreposearch.api.Repo
import java.lang.Exception

sealed class RepoSearchResult {
    data class Loading(val loading: Boolean) : RepoSearchResult()
    data class Success(val data: List<Repo>) : RepoSearchResult()
    data class Error(val error: String) : RepoSearchResult()

}
