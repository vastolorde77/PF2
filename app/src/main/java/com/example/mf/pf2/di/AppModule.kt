package com.example.mf.pf2.di

import android.app.Application
import android.content.Context
import com.example.mf.pf2.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: BaseApplication){

    @Provides
    @Singleton
    fun provideContext() : Context = app

    @Provides
    @Singleton
    fun provideApplication() : Application = app


}