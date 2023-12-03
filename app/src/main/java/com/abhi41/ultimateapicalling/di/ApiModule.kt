package com.abhi41.ultimateapicalling.di

import com.abhi41.ultimateapicalling.data.network.CountryAPi
import com.abhi41.ultimateapicalling.utils.Constants
import com.abhi41.ultimateapicalling.utils.Constants.X_RapidAPI_Host
import com.abhi41.ultimateapicalling.utils.Constants.X_RapidAPI_Key
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CountryAPi {
        return retrofit.create(CountryAPi::class.java)
    }
}