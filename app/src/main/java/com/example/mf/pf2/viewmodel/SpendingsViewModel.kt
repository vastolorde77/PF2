package com.example.mf.pf2.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.mf.pf2.Spendings
import com.example.mf.pf2.network.SpendingsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SpendingsViewModel @Inject constructor(
        private val spendingsAPI: SpendingsAPI
) : ViewModel() {

    var spendingState: MutableLiveData<SpendingsState> = MutableLiveData()
    var postSpendingsState: MutableLiveData<PostSpendingsState> = MutableLiveData()
    var total: MutableLiveData<Int> = MutableLiveData()


    var disposable: CompositeDisposable = CompositeDisposable()


    fun fetchSpendings() {
        disposable.add(spendingsAPI.getSpendings()
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->

                    val list = mutableListOf<Spendings>()

                    Log.d("APIOBS", "$result")

                    var acc = 0
                    // convert to model
                    for (row in result) {
                        list.add(Spendings(row.type, row.amount * 1000, row.product, Date(row.date)))
                        acc += row.amount * 1000
                    }
                    spendingState.postValue(SpendingsState.Success(list))
                    total.postValue(acc)
                }, {
                    spendingState.postValue(SpendingsState.Failed(it.message))
                })
        )
    }

    fun postSpending(spendings: Spendings) {
        disposable.add(spendingsAPI.postSpendings(spendings)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    postSpendingsState.postValue(PostSpendingsState.Success())
                }, {
                    postSpendingsState.postValue(PostSpendingsState.Failed(it.message))
                })
        )
    }

    fun destroy() {
        disposable.dispose()
    }

}