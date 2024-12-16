package com.creators.dec13_vm_app_anton.di

import com.creators.dec13_vm_app_anton.data.api.ApiService
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val baseUrl = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService
            = retrofit.create(ApiService::class.java)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth
        = FirebaseAuth.getInstance()
}