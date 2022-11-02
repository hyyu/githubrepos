package com.hyyu.githubrepos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyyu.githubrepos.ui.model.RepoModel
import com.hyyu.githubrepos.ui.model.toModel
import com.hyyu.githubrepos.network.model.DataState
import com.hyyu.githubrepos.repository.MainRepository
import com.hyyu.githubrepos.ui.event.MainEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val mainRepository: MainRepository = MainRepository()

    private val _reposListLiveData = MutableLiveData<List<RepoModel>>()
    val reposListLiveData: LiveData<List<RepoModel>>
        get() = _reposListLiveData

    private val _snackbarLiveData = MutableLiveData<String>()
    val snackbarLiveData: LiveData<String>
        get() = _snackbarLiveData

    fun launchEvent(event: MainEvent) {
        when (event) {
            is MainEvent.FetchRepos -> fetchRepositoriesFromGithub()
        }
    }

    private fun fetchRepositoriesFromGithub() {
        viewModelScope.launch {
            mainRepository.fetchRepos()
                .onEach { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            val mappedRepos = dataState.data.map { it.toModel() }
                            _reposListLiveData.postValue(mappedRepos)
                        }
                        is DataState.Error -> {
                            _snackbarLiveData.postValue(dataState.exception.message)
                        }
                    }
                }
                .launchIn(this)
        }
    }

}

