package com.hyyu.githubrepos.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RepoObjectResponse(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("full_name")
    @Expose
    val fullName: String,

    @SerializedName("forks")
    @Expose
    val forks: Int,

    @SerializedName("open_issues")
    @Expose
    val openIssues: Int,

    @SerializedName("watchers")
    @Expose
    val watchers: Int
)
