package com.msf.githubissues.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.msf.githubissues.model.ApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val ROOT_URL = "https://api.github.com/repos/JetBrains/kotlin/"

@Module
object RetrofitModule {


    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder().baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .build()
    }

    private fun createGson(): Gson {
        return GsonBuilder().setDateFormat("").create()
    }

}