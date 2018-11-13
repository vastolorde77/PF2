package com.example.mf.pf2.di.modules

import android.arch.lifecycle.ViewModel
import com.example.mf.pf2.network.SpendingsAPI
import com.example.mf.pf2.viewmodel.ReportsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [
    DashboardModule.ProvideViewModel::class
])
abstract class DashboardModule {

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(ReportsViewModel::class)
        fun provideReportsViewModel(spendingsAPI: SpendingsAPI): ViewModel = ReportsViewModel(spendingsAPI)
    }

}