package com.iamkamrul.githubreposearch.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iamkamrul.githubreposearch.api.GithubService
import com.iamkamrul.githubreposearch.api.IN_QUALIFIER
import com.iamkamrul.githubreposearch.api.Repo
import com.iamkamrul.githubreposearch.api.RepoSearchResponse
import com.iamkamrul.githubreposearch.model.RepoSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepoSearchRepository @Inject constructor(
    private val service: GithubService
){
    private val reposList = MutableLiveData<RepoSearchResult>()

    fun fetchSearchRepoList(query:String):LiveData<RepoSearchResult>{
        reposList.value = RepoSearchResult.Loading(true)
        service.searchRepos(
            query = query+IN_QUALIFIER,
            page = 1,
            itemsPerPage = 50
        ).enqueue(object : Callback<RepoSearchResponse>{
            override fun onResponse(call: Call<RepoSearchResponse>, response: Response<RepoSearchResponse>) {
                reposList.value = RepoSearchResult.Loading(false)
                reposList.value = response.body()?.let { repoList->
                    RepoSearchResult.Success(repoList.items)
                }
            }

            override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                reposList.value = RepoSearchResult.Loading(false)
                reposList.value = RepoSearchResult.Error("Something went wrong!")
            }

        })
        return reposList
    }
}