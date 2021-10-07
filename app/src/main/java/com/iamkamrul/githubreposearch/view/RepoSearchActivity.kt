package com.iamkamrul.githubreposearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iamkamrul.githubreposearch.databinding.ActivitySearchRepositoriesBinding

class RepoSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchRepositoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}