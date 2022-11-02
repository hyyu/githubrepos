package com.hyyu.githubrepos.network

import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    companion object Road {
        const val BASE_URL = "https://api.github.com/orgs/jetbrains/"
        const val RETROFIT_TIMEOUT = 10L

        const val REPOS = "repos"
    }

    @GET(REPOS)
    suspend fun fetchRepos(): Response<List<RepoObjectResponse>>

}
