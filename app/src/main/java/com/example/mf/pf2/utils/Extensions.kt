package com.example.mf.pf2.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


fun <T> androidLazy(initializer: () -> T) : Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]

fun ImageView.loadImg(drawable: Drawable){
    Glide.with(context).load(drawable).into(this)
}

fun Int.toNumberFormatted() : String{
    return NumberFormat.getNumberInstance().format(this)
}

fun Int.toCurrency() : String{
    return "Rp." + this.toNumberFormatted()
}

fun Date.toSimpleDateFormat(): String? {
    return SimpleDateFormat("HH:mm:ss dd-MM-yyyy",Locale.US).format(this.time*1000L)
}
