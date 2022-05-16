package com.example.githubpr.model

import com.google.gson.annotations.SerializedName

data class ClosedPR(
    @SerializedName("title") val title:String,
    @SerializedName("created_at") val createdAt:String,
    @SerializedName("closed_at") val closedAt:String,
    @SerializedName("user") val user:User
)