package dev.sertan.android.rovercamera.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sertan.android.rovercamera.util.NasaApiUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideNasaApi(retrofit: Retrofit): NasaApi {
        return retrofit.create(NasaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(NasaApiUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}