package com.example.schooldetailapidemo.ui.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import com.example.schooldetailapidemo.ui.utils.BaseUrl
import com.example.schooldetailapidemo.ui.utils.MyDataStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(dataStore: MyDataStore): Retrofit {
        val httpClient = HttpLoggingInterceptor()
        httpClient.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okhttp = OkHttpClient.Builder()

        okhttp.addNetworkInterceptor(Interceptor { chain ->

            val token = runBlocking {
                dataStore.getToken.first()
            }
            Log.d("Token :- ", token)

            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Authorization", "Bearer $token")
            }.build())
        })

        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create()).client(okhttp.build()).build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): ApiInterFaceService {
        return retrofit.create(ApiInterFaceService::class.java)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideMyDataStore(context: Context): MyDataStore {
        return MyDataStore(context)
    }

}


