package com.example.mf.pf2.di.modules

import dagger.Module

@Module(includes = [ViewModelModule::class])
class AppModule {

//    @Provides
//    @Singleton
//    fun provideContext() : Context = app
//
//    @Provides
//    @Singleton
//    fun provideApplication() : Application = app

//    @Provides
//    @Singleton
//    fun provideViewModelFactory(
//            providers: Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>
//    ) = object : ViewModelProvider.Factory{
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
//        }
//    }

}