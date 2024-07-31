package com.example.lab_ta.di.modules

import com.example.lab_ta.network.api.TvMazeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class TvMazeModule {
    @Provides
    fun provideTvMazeApi(retrofit: Retrofit): TvMazeApi {
        return retrofit.create(TvMazeApi::class.java)
    }

}