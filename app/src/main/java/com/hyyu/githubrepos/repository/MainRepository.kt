package com.hyyu.githubrepos.repository

import com.hyyu.githubrepos.injection.Injection
import com.hyyu.githubrepos.network.HomeApi
import com.hyyu.githubrepos.network.model.RepoObjectResponse
import com.hyyu.githubrepos.network.model.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository {

    companion object {
        const val DEFAULT_REQUEST_ERROR = "Request failed. Error:"
    }

    private val apiController: HomeApi = Injection.provideApi(Injection.provideGsonBuilder())

    suspend fun fetchRepos(): Flow<DataState<List<RepoObjectResponse>>> = flow {
        try {
            apiController.fetchRepos().also { response ->
                if (response.isSuccessful) emit(
                    DataState.Success(
                        response.body()
                            ?: throw Exception("$DEFAULT_REQUEST_ERROR ${response.code()}")
                    )
                ) else throw Exception("$DEFAULT_REQUEST_ERROR ${response.code()}")
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}
