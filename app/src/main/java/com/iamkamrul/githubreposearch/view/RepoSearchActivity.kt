package com.iamkamrul.githubreposearch.view

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.iamkamrul.githubreposearch.databinding.ActivitySearchRepositoriesBinding
import com.iamkamrul.githubreposearch.di.viewmodel.AppViewModelFactory
import com.iamkamrul.githubreposearch.domain.entity.RepoSearchResult
import javax.inject.Inject

class RepoSearchActivity : AppCompatActivity() {

    @Inject lateinit var appViewModelFactory: AppViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchRepositoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this, appViewModelFactory).get(RepoSearchViewModel::class.java)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(decoration)

        binding.bindState(viewModel,viewModel.accept)

    }


    private fun ActivitySearchRepositoriesBinding.bindState(
        viewModel: RepoSearchViewModel,
        uiActions: (UiAction) -> Unit
    ){
        val adapter = ReposAdapter()
        list.adapter = adapter

        bindList(viewModel, adapter)
        bindSearch(uiActions)
    }

    private fun ActivitySearchRepositoriesBinding.bindSearch(
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        searchRepo.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchRepo.text.trim().let {
                    if (it.isNotEmpty()) {
                        list.scrollToPosition(0)
                        onQueryChanged(UiAction.Search(searchText = it.toString()))
                    }
                }
                true
            } else {
                false
            }
        }
    }

    private fun ActivitySearchRepositoriesBinding.bindList(
        viewModel: RepoSearchViewModel,
        repoAdapter: ReposAdapter,
    ) {
        viewModel.reposSearchApiResponse.observe(this@RepoSearchActivity) { result ->
            when (result) {
                is RepoSearchResult.Success -> repoAdapter.submitList(result.data)
                is RepoSearchResult.Loading -> progressCircular.isVisible = result.loading
                is RepoSearchResult.Error -> Toast.makeText(applicationContext, result.error, Toast.LENGTH_LONG).show()

            }
        }
    }


}