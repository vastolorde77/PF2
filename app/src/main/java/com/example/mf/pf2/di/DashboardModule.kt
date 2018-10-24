package com.example.mf.pf2.di

import com.example.mf.pf2.utils.ChartGenerator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DashboardModule{

    @Provides
    @Singleton
    fun provideChartGenerator() = ChartGenerator()

}