package com.hyyu.githubrepos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.hyyu.githubrepos.databinding.ActivityMainBinding
import com.hyyu.githubrepos.ui.event.MainEvent

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        observeSnackbar()
        observeReposList()

        viewModel.launchEvent(MainEvent.FetchRepos)
    }

    private fun observeReposList() {
        viewModel.reposListLiveData.observe(this) { repos ->
        }
    }

    private fun observeSnackbar() {
        viewModel.snackbarLiveData.observe(this) { message ->
            val rootView = findViewById<View>(android.R.id.content)
            Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
        }
    }

}
