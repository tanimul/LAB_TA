package com.example.lab_ta.di

import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.annotation.Keep
import com.example.lab_ta.network.core.MyServiceInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
@Module
@InstallIn(ActivityRetainedComponent::class)
@Keep
object ActivityModule {

    @Provides
    fun provideNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }

    @Provides
    fun provideNetworkInterceptor(
        interceptor: MyServiceInterceptor
    ): OkHttpClient {
        val build = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES).addInterceptor(interceptor)

        //if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        build.addInterceptor(httpLoggingInterceptor)
        //}

        return build.build()
    }

    @Provides
    fun provideRetrofitInstance(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

}