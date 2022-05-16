package com.example.githubpr.network.repository

import com.example.githubpr.model.ClosedPR
import com.example.githubpr.network.NetworkCallConnector
import com.example.githubpr.network.NetworkCallResult
import kotlinx.coroutines.Dispatchers

interface GithubRepository {
    suspend fun getClosedPRs(user: String, repoName: String): NetworkCallResult<List<ClosedPR>>
}
class GithubRepositoryImpl: GithubRepository {
    private val apiService = NetworkCallConnector.getApiService("https://api.github.com/")

    override suspend fun getClosedPRs(user: String, repoName: String): NetworkCallResult<List<ClosedPR>> {
        return NetworkCallConnector.callWebService(Dispatchers.Default) {
            apiService.getGithubPRs(user, repoName, "closed")
        }
    }

}