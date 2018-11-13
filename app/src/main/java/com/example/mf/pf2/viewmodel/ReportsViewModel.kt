package com.example.mf.pf2.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.mf.pf2.network.SpendingsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReportsViewModel @Inject constructor(
        private val spendingsAPI: SpendingsAPI
) : ViewModel() {

    var reports: MutableLiveData<List<List<Int>>> = MutableLiveData()
    var disposable: CompositeDisposable = CompositeDisposable()

    fun fetchReports() {
        disposable.add(spendingsAPI.getReports()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val list = it.takeLast(3).map { report ->
                        report.reports.takeLast(7)
                    }
                    reports.postValue(list)
                }
        )
    }

    fun destroy() {
        disposable.dispose()
    }
}