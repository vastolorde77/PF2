package com.example.mf.pf2

import android.app.Application
import com.example.mf.pf2.di.ActivityComponent
import com.example.mf.pf2.di.AppModule
import com.example.mf.pf2.di.DaggerActivityComponent
import com.squareup.leakcanary.LeakCanary

class BaseApplication : Application() {

    companion object {
        lateinit var activityComponent: ActivityComponent
    }

    override fun onCreate() {
        super.onCreate()
        this.initializeDependencyInjection()
        this.initializeLeakDetection()
    }

    fun initializeDependencyInjection(){
        activityComponent = DaggerActivityComponent.builder().appModule(AppModule(this)).build()
    }

    fun initializeLeakDetection(){
        if (LeakCanary.isInAnalyzerProcess(this)){
            return
        }
        LeakCanary.install(this)
    }

}