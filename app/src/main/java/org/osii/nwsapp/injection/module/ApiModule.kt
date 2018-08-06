package org.osii.nwsapp.injection.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.osii.nwsapp.BuildConfig
import org.osii.nwsapp.data.remote.ApiService
import org.osii.nwsapp.util.RxThreadingWithErrorHandlingCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().also {
            it.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
    }


    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient, gson: Gson): ApiService {
        return Retrofit.Builder()
                .client(okHttpClient)
                .validateEagerly(true)
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(
                        RxThreadingWithErrorHandlingCallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

}