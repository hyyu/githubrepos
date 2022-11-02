package com.hyyu.githubrepos.ui.model

import com.hyyu.githubrepos.network.model.RepoObjectResponse

data class RepoModel(
    val id: Int,
    val fullName: String,
    val forks: Int,
    val openIssues: Int,
    val watchers: Int,
    val description: String?
)

fun RepoObjectResponse.toModel(): RepoModel =
    RepoModel(
        id = id,
        fullName = fullName,
        forks = forks,
        openIssues = openIssues,
        watchers = watchers,
        description = description
    )
