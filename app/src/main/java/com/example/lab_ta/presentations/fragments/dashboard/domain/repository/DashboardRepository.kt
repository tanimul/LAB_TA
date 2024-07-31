package com.example.lab_ta.presentations.fragments.dashboard.domain.repository

import android.net.ConnectivityManager
import com.example.lab_ta.network.api.TvMazeApi
import com.example.lab_ta.network.core.Resource
import com.example.lab_ta.network.core.networkCall
import com.example.lab_ta.presentations.fragments.dashboard.domain.model.Contents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val tvMazeApi: TvMazeApi,
    private val connectivityManager: ConnectivityManager
) {

    fun getContent(
        query: String
    ): Flow<Resource<List<Contents>>> {
        return networkCall(connectivityManager) {
            tvMazeApi.getShow(query)
        }.flowOn(Dispatchers.IO)
    }

}