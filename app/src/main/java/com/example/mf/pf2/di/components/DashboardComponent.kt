package com.example.mf.pf2.di.components

import com.example.mf.pf2.di.DashboardScope
import com.example.mf.pf2.di.modules.DashboardModule
import com.example.mf.pf2.ui.fragments.DashboardFragment
import dagger.Subcomponent

@DashboardScope
@Subcomponent(modules = [DashboardModule::class])
interface DashboardComponent {
    fun inject(dashboardFragment: DashboardFragment)
}