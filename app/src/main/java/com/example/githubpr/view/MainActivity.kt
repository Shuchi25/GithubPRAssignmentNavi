package com.example.githubpr.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubpr.R
import com.example.githubpr.databinding.ActivityMainBinding
import com.example.githubpr.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val peopleAdapter: ClosedPrAdapter by lazy {
        ClosedPrAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerView.apply {
            adapter = peopleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        setObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getClosedPRs("Shuchi25", "GithubPRAssignmentNavi")
    }

    private fun setObservers() {
        viewModel.peopleLiveData.observe(this) {
            peopleAdapter.setList(it)
        }
    }
}