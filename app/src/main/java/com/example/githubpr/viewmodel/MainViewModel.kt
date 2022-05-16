package com.example.githubpr.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubpr.model.ClosedPR
import com.example.githubpr.network.NetworkCallResult
import com.example.githubpr.network.repository.GithubRepository
import com.example.githubpr.network.repository.GithubRepositoryImpl
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private var closedPRMutableLiveData = MutableLiveData<List<ClosedPR>>()
    val closedPRLiveData: LiveData<List<ClosedPR>> = closedPRMutableLiveData

    private val githubRepository: GithubRepository = GithubRepositoryImpl()

    fun getClosedPRs(username: String, repoName: String) {
        viewModelScope.launch {
            when(val result = githubRepository.getClosedPRs(username, repoName)) {
                is NetworkCallResult.Success -> {
                    closedPRMutableLiveData.value = result.value
                    Log.d("TAG success", result.value.toString())
                }
                is NetworkCallResult.Error -> {
                    Log.d("TAG", "error")
                }
            }
            Log.d("TAG", "getClosedPRs called")
        }
    }
}