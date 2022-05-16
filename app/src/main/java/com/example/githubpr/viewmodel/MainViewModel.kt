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
    private var peopleMutableLiveData = MutableLiveData<List<ClosedPR>>()
    val peopleLiveData: LiveData<List<ClosedPR>> = peopleMutableLiveData

    private val githubRepository: GithubRepository = GithubRepositoryImpl()

    fun getClosedPRs(username: String, repoName: String) {
        viewModelScope.launch {
            when(val result = githubRepository.getClosedPRs(username, repoName)) {
                is NetworkCallResult.Success -> {
                    peopleMutableLiveData.value = result.value
                    Log.d("TAG", result.value.toString())
                }
                is NetworkCallResult.Error -> {

                }
            }
            Log.d("TAG", "getClosedPRs called")
        }
    }
}