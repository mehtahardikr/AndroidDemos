package com.hm.demo2.repository

import com.hm.demo2.webservice.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}