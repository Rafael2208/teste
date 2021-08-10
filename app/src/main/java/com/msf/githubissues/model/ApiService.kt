package com.msf.githubissues.model

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("issues")
    fun callIssues(): Call<List<Issue>>

}