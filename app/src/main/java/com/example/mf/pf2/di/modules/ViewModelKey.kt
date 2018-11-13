package com.example.mf.pf2.di.modules

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.PROPERTY_GETTER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(
        val value: KClass<out ViewModel>
)


