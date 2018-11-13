package com.example.mf.pf2.di.modules

import android.arch.lifecycle.ViewModel
import com.example.mf.pf2.di.SpendingsScope
import com.example.mf.pf2.network.SpendingsAPI
import com.example.mf.pf2.viewmodel.SpendingsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

//@Module(includes = [SpendingsListModule.ProvideViewModel::class])
//abstract class SpendingsListModule{
//
//    @Module
//    class ProvideViewModel {
//        @Provides
//        @SpendingsScope
//        @IntoMap
//        @ViewModelKey(SpendingsViewModel::class)
//        fun provideReportsViewModel(spendingsAPI: SpendingsAPI): ViewModel = SpendingsViewModel(spendingsAPI)
//    }
//}

@Module(includes = [SpendingsListModule.ProvideViewModel::class])
class SpendingsListModule {

    @Module
    class ProvideViewModel {
        @Provides
        @SpendingsScope
        @IntoMap
        @ViewModelKey(SpendingsViewModel::class)
        fun provideReportsViewModel(spendingsAPI: SpendingsAPI): ViewModel = SpendingsViewModel(spendingsAPI)
    }
}