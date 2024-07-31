package com.example.lab_ta.network.api

import com.example.lab_ta.common.constants.ApiConstants.API_SEARCH
import com.example.lab_ta.presentations.fragments.dashboard.domain.model.Contents
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TvMazeApi {
    @POST(API_SEARCH)
    fun getShow(@Query("q") query: String): Response<List<Contents>>
}