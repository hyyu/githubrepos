package com.hyyu.githubrepos.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hyyu.githubrepos.network.HomeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object Injection {
        fun provideApi(gson: Gson): HomeApi {
            val loggingInterceptor = HttpLoggingInterceptor()
            val clientBuilder = OkHttpClient.Builder().apply {
                connectTimeout(HomeApi.RETROFIT_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(HomeApi.RETROFIT_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(HomeApi.RETROFIT_TIMEOUT, TimeUnit.SECONDS)
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }

            return Retrofit.Builder()
                .baseUrl(HomeApi.BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(HomeApi::class.java)
        }

        fun provideGsonBuilder(): Gson {
            return GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
        }
    }
