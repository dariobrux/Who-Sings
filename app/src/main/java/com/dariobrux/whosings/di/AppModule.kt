package com.dariobrux.whosings.di

import android.content.Context
import com.dariobrux.whosings.BuildConfig
import com.dariobrux.whosings.common.Constants
import com.dariobrux.whosings.data.database.WhoSingsDAO
import com.dariobrux.whosings.data.database.WhoSingsDatabase
import com.dariobrux.whosings.data.remote.ApiHelper
import com.dariobrux.whosings.data.remote.ApiService
import com.dariobrux.whosings.data.repository.GameRepository
import com.dariobrux.whosings.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 * This singleton object is a bucket from where we will get all the dependencies from.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient
            .Builder()
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .hostnameVerifier { _, _ -> true }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideWeatherApiHelper(service: ApiService) = ApiHelper(service)

    @Singleton
    @Provides
    fun provideRepository(dao: WhoSingsDAO) = Repository(dao)

    @Singleton
    @Provides
    fun provideGameRepository(api: ApiHelper) = GameRepository(api)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = WhoSingsDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideDAO(db: WhoSingsDatabase) = db.whoSingsDAO()
}
