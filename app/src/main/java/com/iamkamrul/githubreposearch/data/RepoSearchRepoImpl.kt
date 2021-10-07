package com.iamkamrul.githubreposearch.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iamkamrul.githubreposearch.domain.entity.RepoSearchResponseEntity
import com.iamkamrul.githubreposearch.domain.repo.RepoSearchRepository
import com.iamkamrul.githubreposearch.domain.usecase.RepoSearchApiUseCase
import com.iamkamrul.githubreposearch.domain.entity.RepoSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepoSearchRepoImpl @Inject constructor(
    private val service: GithubService
) :RepoSearchRepository{
    private val reposList = MutableLiveData<RepoSearchResult>()

    override fun searchRepo(params: RepoSearchApiUseCase.Params): LiveData<RepoSearchResult> {
        reposList.value = RepoSearchResult.Loading(true)
        service.searchRepos(
            query = params.searchQuery+IN_QUALIFIER,
            page = 1,
            itemsPerPage = 50
        ).enqueue(object : Callback<RepoSearchResponseEntity> {
            override fun onResponse(call: Call<RepoSearchResponseEntity>, response: Response<RepoSearchResponseEntity>) {
                reposList.value = RepoSearchResult.Loading(false)
                reposList.value = response.body()?.let { repoList->
                    RepoSearchResult.Success(repoList.items)
                }
            }

            override fun onFailure(call: Call<RepoSearchResponseEntity>, t: Throwable) {
                reposList.value = RepoSearchResult.Loading(false)
                reposList.value = RepoSearchResult.Error("Something went wrong!")
            }

        })
        return reposList
    }

}