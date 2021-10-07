package com.iamkamrul.githubreposearch.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.iamkamrul.githubreposearch.domain.repo.RepoSearchRepository
import com.iamkamrul.githubreposearch.domain.usecase.RepoSearchApiUseCase
import javax.inject.Inject

class RepoSearchViewModel @Inject constructor(
    private val repoSearchApiUseCase: RepoSearchApiUseCase
) : ViewModel(){

    val accept: (UiAction) -> Unit
    private val searchTextQuery = MutableLiveData("Android")

    init {
        accept = { action ->
            when (action) {
                is UiAction.Search -> searchTextQuery.value = action.searchText
                is UiAction.LoginBtnClick -> {}
            }
        }
    }

    val reposSearchApiResponse = searchTextQuery.switchMap {searchText->
        repoSearchApiUseCase.execute(RepoSearchApiUseCase.Params(searchText))
    }


}

sealed class UiAction {
    data class Search(val searchText: String) : UiAction()
    data class LoginBtnClick(val phoneNumber: String,val password:String) : UiAction()
}