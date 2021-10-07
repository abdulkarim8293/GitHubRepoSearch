package com.iamkamrul.githubreposearch.domain.repo

import androidx.lifecycle.LiveData
import com.iamkamrul.githubreposearch.domain.usecase.RepoSearchApiUseCase
import com.iamkamrul.githubreposearch.domain.entity.RepoSearchResult

interface RepoSearchRepository{
    fun searchRepo(params:RepoSearchApiUseCase.Params):LiveData<RepoSearchResult>
}