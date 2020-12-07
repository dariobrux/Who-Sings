package com.dariobrux.whosings.di

import android.content.Context
import com.dariobrux.whosings.data.local.WhoSingsDAO
import com.dariobrux.whosings.data.local.WhoSingsDatabase
import com.dariobrux.whosings.ui.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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

//    @Provides
//    fun provideBaseUrl() = SyncStateContract.Constants.BASE_URL
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient
//            .Builder()
//            .hostnameVerifier { _, _ -> true }
//            .addInterceptor(loggingInterceptor)
//            .build()
//    } else {
//        OkHttpClient
//            .Builder()
//            .hostnameVerifier { _, _ -> true }
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
//        .addConverterFactory(MoshiConverterFactory.create())
//        .baseUrl(baseUrl)
//        .client(okHttpClient)
//        .build()
//
//    @Provides
//    @Singleton
//    fun provideWeatherService(retrofit: Retrofit): WeatherService = retrofit.create(WeatherService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideWeatherApiHelper(service: WeatherService) = WeatherApiHelper(service)

    @Singleton
    @Provides
    fun provideLoginRepository(dao: WhoSingsDAO) = LoginRepository(dao)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = WhoSingsDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideDAO(db: WhoSingsDatabase) = db.whoSingsDAO()
}
