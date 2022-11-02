package com.hyyu.githubrepos

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    fun launchEvent(event: MainEvent) {
        when (event) {
            is MainEvent.FetchRepos -> {} // fetchRepositoriesFromGithub()
        }
    }

}

