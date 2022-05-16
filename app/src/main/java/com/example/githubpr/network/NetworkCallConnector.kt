package com.example.githubpr.network

import com.example.githubpr.network.service.GithubService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkCallConnector {

    suspend fun <T> callWebService(
        dispatcher: CoroutineDispatcher,
        webServiceCall: suspend () -> T
    ): NetworkCallResult<T> {
        return withContext(dispatcher) {
            val throwable: Throwable
            try {
                return@withContext NetworkCallResult.Success(webServiceCall.invoke())
            } catch (exception: Throwable) {
                throwable = exception
            }
            return@withContext NetworkCallResult.Error(throwable)
        }
    }

    fun getApiService(baseUrl: String): GithubService {
        val retrofit = getRetrofit(baseUrl)
        return retrofit.create(GithubService::class.java)
    }

    private fun getRetrofit(baseUrl: String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}