package com.example.mf.pf2.di.modules

import com.example.mf.pf2.ui.activities.AddSpendingsActivity
import com.example.mf.pf2.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeAddSpendingsActivity(): AddSpendingsActivity
}