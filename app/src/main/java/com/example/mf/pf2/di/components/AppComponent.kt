package com.example.mf.pf2.di.components

import android.app.Application
import com.example.mf.pf2.BaseApplication
import com.example.mf.pf2.di.modules.AppModule
import com.example.mf.pf2.di.modules.MainActivityModule
import com.example.mf.pf2.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    MainActivityModule::class
])
interface AppComponent {
//    fun plus(spendingsListModule: SpendingsListModule) : SpendingsComponent
//    fun plus(dashboardModule: DashboardModule) : DashboardComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(baseApplication: BaseApplication)
}