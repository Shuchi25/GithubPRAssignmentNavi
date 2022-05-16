package com.example.githubpr.network.service

import com.example.githubpr.model.ClosedPR
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("repos/{Shuchi25}/{GithubPRAssignmentNavi}/pulls")
    suspend fun getGithubPRs(
        @Path("username") username: String,
        @Path("repoName") repoName: String,
        @Query("state") state: String
    ): List<ClosedPR>
}