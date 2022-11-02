package com.hyyu.githubrepos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hyyu.githubrepos.R
import com.hyyu.githubrepos.databinding.ActivityMainBinding
import com.hyyu.githubrepos.ui.adapter.ItemClickListener
import com.hyyu.githubrepos.ui.adapter.ReposAdapter
import com.hyyu.githubrepos.ui.event.MainEvent

class MainActivity : AppCompatActivity(), ItemClickListener {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        setupSwipeToRefresh()

        supportActionBar?.title = getString(R.string.app_name)
        setupReposList()

        observeSnackbar()
        observeReposList()

        viewModel.launchEvent(MainEvent.FetchRepos)
    }

    private fun setupSwipeToRefresh() {
        binding.layoutRefresh.setOnRefreshListener {
            binding.layoutRefresh.isRefreshing = false
            viewModel.launchEvent(MainEvent.FetchRepos)
        }
    }

    private fun setupReposList() {
        adapter = ReposAdapter(this, this)
        binding.rvRepos.layoutManager = LinearLayoutManager(this)
        binding.rvRepos.adapter = adapter
    }

    override fun onItemClicked(position: Int) {
        val item = adapter.getItem(position)
        val intent = Intent(this, DetailActivity::class.java)

        intent.putExtra(DetailActivity.REPOSITORY_FULL_NAME, item.fullName)
        intent.putExtra(DetailActivity.REPOSITORY_DESCRIPTION, item.description)

        startActivity(intent)
    }

    private fun setVisibilityAccordingToFetchResult(isError: Boolean) {
        binding.apply {
            if (isError) {
                rvRepos.visibility = View.INVISIBLE
                tvNoReposAvailable.visibility = View.VISIBLE
            } else {
                rvRepos.visibility = View.VISIBLE
                tvNoReposAvailable.visibility = View.INVISIBLE
            }
        }
    }

    private fun observeReposList() {
        viewModel.reposListLiveData.observe(this) { repos ->
            adapter.items = repos
            setVisibilityAccordingToFetchResult(repos.isEmpty())
        }
    }

    private fun observeSnackbar() {
        viewModel.snackbarLiveData.observe(this) { message ->
            val rootView = findViewById<View>(android.R.id.content)
            Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
            setVisibilityAccordingToFetchResult(true)
        }
    }

}
