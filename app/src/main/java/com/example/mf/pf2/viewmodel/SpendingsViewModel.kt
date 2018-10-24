package com.example.mf.pf2.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.mf.pf2.Spendings
import com.example.mf.pf2.network.SpendingsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit


class SpendingsViewModel  : ViewModel(){

    var spendings : MutableLiveData<SpendingsState> = MutableLiveData()
    var total : MutableLiveData<Int> = MutableLiveData()


    lateinit var disposable : Disposable



    fun fetchSpendings(spendingsAPI : SpendingsAPI) {
        disposable = spendingsAPI.getSpendings()
                .delay(3,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->

                    val list = mutableListOf<Spendings>()

                    Log.d("APIOBS","$result")

                    var acc = 0
                    // convert to model
                    for (row in result) {
                        list.add(Spendings(row.type,row.amount * 1000,row.product,Date(row.date)))
                        acc+=row.amount * 1000
                    }
                    spendings.postValue(SpendingsState.Success(list))
                    total.postValue(acc)
                })
    }

}