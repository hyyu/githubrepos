package com.hyyu.githubrepos

sealed class MainEvent {
    object FetchRepos : MainEvent()
}
