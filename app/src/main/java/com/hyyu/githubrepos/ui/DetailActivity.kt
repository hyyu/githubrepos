package com.hyyu.githubrepos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyyu.githubrepos.R
import com.hyyu.githubrepos.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val REPOSITORY_FULL_NAME = "REPOSITORY_FULL_NAME"
        const val REPOSITORY_DESCRIPTION = "REPOSITORY_DESCRIPTION"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.detail_page_title)

        val fullName = intent.getStringExtra(REPOSITORY_FULL_NAME)
        val description = intent.getStringExtra(REPOSITORY_DESCRIPTION) ?: getString(R.string.detail_no_description)

        binding.tvRepoTitle.text = fullName
        binding.tvRepoDescription.text = description
    }

}
