package com.example.mf.pf2.di

import com.example.mf.pf2.ui.fragments.DashboardFragment
import com.example.mf.pf2.ui.fragments.SpendingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DashboardModule::class, NetworkModule::class])
interface ActivityComponent{
    fun inject(dashboardFragment: DashboardFragment)
    fun inject(spendingsFragment: SpendingsFragment)
}