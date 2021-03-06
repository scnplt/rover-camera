package dev.sertan.android.rovercamera.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sertan.android.rovercamera.util.NasaApiUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This module is for dependency injection with Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideNasaApi(retrofit: Retrofit): NasaApi = retrofit.create(NasaApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(NasaApiUtils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
}