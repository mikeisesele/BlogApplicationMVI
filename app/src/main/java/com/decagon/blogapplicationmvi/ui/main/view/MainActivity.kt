package com.decagon.blogapplicationmvi.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.blogapplicationmvi.data.api.ApiHelperImpl
import com.decagon.blogapplicationmvi.data.api.RetrofitBuilder
import com.decagon.blogapplicationmvi.data.model.Post
import com.decagon.blogapplicationmvi.databinding.ActivityMainBinding
import com.decagon.blogapplicationmvi.ui.main.adapter.MainAdapter
import com.decagon.blogapplicationmvi.ui.main.intent.MainIntent
import com.decagon.blogapplicationmvi.ui.main.viewmodel.MainViewModel
import com.decagon.blogapplicationmvi.ui.main.viewstate.MainState
import com.decagon.blogapplicationmvi.util.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity: AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private val adapter = MainAdapter(arrayListOf())
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var postButton: FloatingActionButton
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?){


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        recyclerView = binding.recyclerView
        searchView =  binding.searchView
        postButton = binding.floatingAddPostButton
        progressBar = binding.progressBar


        setUpUI()
        setUpViewModel()
        observeViewModel()
        lunchView()

    }

    private fun setUpUI(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(recyclerView.context,(recyclerView.layoutManager as LinearLayoutManager).orientation)
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setUpViewModel(){
            mainViewModel = ViewModelProvider(
                this, ViewModelFactory (ApiHelperImpl(RetrofitBuilder.API_SERVICE)))
                .get(MainViewModel::class.java)
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            mainViewModel.state.collect{
                Log.d("post", it.toString())
                when (it){

                    is MainState.Idle -> {}

                    is MainState.Loading -> {
                        postButton.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Posts -> {
                        postButton.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        renderList(it.post)
                    }

                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun lunchView(){
        lifecycleScope.launch{
            mainViewModel.userIntent.send(MainIntent.FetchPost)
        }
    }


    private fun renderList(post: List<Post>) {
        recyclerView.visibility = View.VISIBLE
        post.let { it -> it.let {adapter.addData(it)}
            adapter.notifyDataSetChanged()
        }
    }
}