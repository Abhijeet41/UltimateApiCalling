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
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept" ,"application/json")
                    .addHeader("X-RapidAPI-Key" ,X_RapidAPI_Key)
                    .addHeader("X-RapidAPI-Host" ,X_RapidAPI_Host)
                    //.addHeader("Authorization", "Bearer ${Constants.bearer_token}")
                    .build()
                chain.proceed(request)
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ) =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CountryAPi {
        return retrofit.create(CountryAPi::class.java)
    }
}