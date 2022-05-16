package com.example.githubpr.network.service

import com.example.githubpr.model.ClosedPR
import retrofit2.http.GET

interface GithubService {

    @GET("/api/people/")
    suspend fun getClosedPRs(): List<ClosedPR>
}