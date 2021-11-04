package com.hm.demo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hm.demo2.databinding.ActivityMainBinding
import com.hm.demo2.repository.MainRepository
import com.hm.demo2.viewmodel.MainViewModel
import com.hm.demo2.viewmodel.MyViewModelFactory
import com.hm.demo2.webservice.RetrofitService

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllMovies()
    }
}