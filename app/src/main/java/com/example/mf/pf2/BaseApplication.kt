package com.example.mf.pf2

import android.app.Activity
import android.app.Application
import com.example.mf.pf2.di.AppInjector
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector


    override fun onCreate() {
        super.onCreate()
        this.initializeDependencyInjection()
        this.initializeLeakDetection()
    }

    fun initializeDependencyInjection() {
        AppInjector.init(this)
    }

    fun initializeLeakDetection() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

}