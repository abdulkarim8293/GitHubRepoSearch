package com.iamkamrul.githubreposearch.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

class RepoSearchViewModel(
    private val repository: RepoSearchRepository
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
        repository.fetchSearchRepoList(searchText)
    }


}

sealed class UiAction {
    data class Search(val searchText: String) : UiAction()
    data class LoginBtnClick(val phoneNumber: String,val password:String) : UiAction()
}