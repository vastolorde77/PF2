package com.example.mf.pf2.di.modules

import com.example.mf.pf2.network.SpendingsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://5a2eafbf0e07b70012083a9a.mockapi.io")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideSpendingsService(retrofit: Retrofit): SpendingsService {
        return retrofit.create(SpendingsService::class.java)
    }

}