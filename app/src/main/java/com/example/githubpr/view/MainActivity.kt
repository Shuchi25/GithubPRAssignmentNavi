package com.example.githubpr.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubpr.R
import com.example.githubpr.databinding.ActivityMainBinding
import com.example.githubpr.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val closedPrAdapter: ClosedPrAdapter by lazy {
        ClosedPrAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerView.apply {
            adapter = closedPrAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        setObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getClosedPRs("Shuchi25", "GithubPRAssignmentNavi")
    }

    private fun setObservers() {
        viewModel.closedPRLiveData.observe(this) { closedPrList ->
            closedPrAdapter.setList(closedPrList)
            if (closedPrList.isNotEmpty()) {
                binding.nameTv.text = closedPrList[0].user.login
                Glide.with(this)
                    .load(
                        closedPrList[0].user.avatarUrl
                    )
                    .into(binding.profileIv)
            }
        }
    }
}