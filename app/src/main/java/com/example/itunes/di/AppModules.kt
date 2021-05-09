package com.example.itunes.di

import android.content.Context
import androidx.room.Room
import com.example.itunes.BuildConfig
import com.example.itunes.network.BaseAPIService
import com.example.itunes.room.HistoryDao
import com.example.itunes.room.HistoryDatabase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    /**
     * Returns the [HttpLoggingInterceptor] instance with logging level set to body according to app debug level
     */
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }

    /**
     * Provides an [OkHttpClient]
     * @param loggingInterceptor [HttpLoggingInterceptor] instance
     */
    @Provides
    fun provideOKHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().run {
            connectTimeout(120L, TimeUnit.SECONDS)
            readTimeout(120L, TimeUnit.SECONDS)
            writeTimeout(120L, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            build()
        }

    /**
     * Provides an [Retrofit]
     * @param okHttpClient [OkHttpClient] instance
     */
    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().run {
            baseUrl("https://itunes.apple.com/")
            client(okHttpClient)
            addConverterFactory(
                Json { isLenient = true }.asConverterFactory("application/json".toMediaType())
            )
            build()
        }

    /**
     * Provides an [BaseAPIService]
     * @param retrofit [Retrofit] instance
     */
    @Provides
    @Singleton
    fun provideBaseApi(retrofit: Retrofit): BaseAPIService =
        retrofit.create(BaseAPIService::class.java)

    /**
     * Provides an [HistoryDatabase]
     * @param context[ApplicationContext] instance
     */
    @Singleton
    @Provides
    fun provideHistoryDb(@ApplicationContext context: Context): HistoryDatabase =
        Room.databaseBuilder(
            context, HistoryDatabase::class.java,
            HistoryDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    /**
     * Provides an [HistoryDao]
     * @param historyDatabase[HistoryDatabase] instance
     */
    @Singleton
    @Provides
    fun provideSongsDAO(historyDatabase: HistoryDatabase): HistoryDao =
        historyDatabase.historyDao()
}