package com.example.lab_ta.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.Keep
import com.example.lab_ta.common.constants.AppConstants
import com.example.lab_ta.db.room.MyRoomDatabase
import com.example.lab_ta.presentations.fragments.login.data.LoginRepositoryImpl
import com.example.lab_ta.presentations.fragments.login.domain.repository.LoginRepository
import com.example.lab_ta.presentations.fragments.profile.data.ProfileRepositoryImpl
import com.example.lab_ta.presentations.fragments.profile.domain.repository.ProfileRepository
import com.example.lab_ta.presentations.fragments.registration.data.RegistrationRepositoryImpl
import com.example.lab_ta.presentations.fragments.registration.domain.repository.RegistrationRepository
import com.example.lab_ta.utils.DataStorePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
@Keep
object AppModule {
    @Provides
    @Named("base_url")
    fun providesBaseUrl(): String {
        return AppConstants.API_URL
    }

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideRoomInstance(application: Application): MyRoomDatabase {
        return MyRoomDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStorePreferences {
        return DataStorePreferences(context)
    }

    @Provides
    fun provideRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository =
        registrationRepositoryImpl

    @Provides
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository =
        loginRepositoryImpl

    @Provides
    fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository =
        profileRepositoryImpl


}