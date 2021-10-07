package com.iamkamrul.githubreposearch.domain.usecase

import androidx.lifecycle.LiveData
import com.iamkamrul.githubreposearch.domain.entity.RepoSearchResult
import com.iamkamrul.githubreposearch.domain.repo.RepoSearchRepository
import javax.inject.Inject

class RepoSearchApiUseCase @Inject constructor(
    private val repository: RepoSearchRepository
):ApiUseCase<RepoSearchApiUseCase.Params,RepoSearchResult>{
    data class Params(val searchQuery:String)

    override fun execute(params: Params?): LiveData<RepoSearchResult> {
        return repository.searchRepo(params!!)
    }
}