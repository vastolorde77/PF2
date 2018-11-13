package com.example.mf.pf2.di.modules

import com.example.mf.pf2.ui.fragments.DashboardFragment
import com.example.mf.pf2.ui.fragments.SpendingsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSpendingsListFragment(): SpendingsListFragment

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

}