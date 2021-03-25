package dev.sertan.android.rovercamera.util

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sertan.android.rovercamera.data.NasaApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    @Singleton
    fun provideNasaApi(): NasaApi = Retrofit.Builder()
        .baseUrl(NasaApiUtils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NasaApi::class.java)
}