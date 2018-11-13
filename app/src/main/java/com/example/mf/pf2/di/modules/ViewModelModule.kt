package com.example.mf.pf2.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.mf.pf2.viewmodel.ReportsViewModel
import com.example.mf.pf2.viewmodel.SpendingsViewModel
import com.example.mf.pf2.viewmodel.SpendingsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SpendingsViewModel::class)
    abstract fun bindSpendingsViewModel(spendingsViewModel: SpendingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReportsViewModel::class)
    abstract fun bindReportsViewModel(reportsViewMode: ReportsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: SpendingsViewModelFactory): ViewModelProvider.Factory
}