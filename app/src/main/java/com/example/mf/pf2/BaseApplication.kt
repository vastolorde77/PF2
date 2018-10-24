package com.example.mf.pf2

import android.app.Application
import com.example.mf.pf2.di.ActivityComponent
import com.example.mf.pf2.di.AppModule
import com.example.mf.pf2.di.DaggerActivityComponent

class BaseApplication : Application() {

    companion object {
        lateinit var activityComponent: ActivityComponent
    }

    override fun onCreate() {
        super.onCreate()
        activityComponent = DaggerActivityComponent.builder().appModule(AppModule(this)).build()
    }

}