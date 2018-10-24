package com.example.mf.pf2.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.mf.pf2.ReportsResponse
import com.example.mf.pf2.network.SpendingsAPI
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ReportsViewModel : ViewModel(){

    var reports : MutableLiveData<List<List<Int>>> = MutableLiveData()
    lateinit var disposable: Disposable

    fun fetchReports(spendingsAPI: SpendingsAPI){
        disposable = spendingsAPI.getReports()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val list = it.takeLast(3).map{
                        report -> report.reports.takeLast(7)
                    }
                    reports.postValue(list)
                })
    }

    fun destroy() {
        disposable.dispose()
    }
}