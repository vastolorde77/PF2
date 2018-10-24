package com.example.mf.pf2.network

import com.example.mf.pf2.ReportsResponse
import com.example.mf.pf2.SpendingsResponse
import io.reactivex.Flowable
import javax.inject.Inject

class SpendingsAPI @Inject constructor(private val SpendingsService : SpendingsService){
    fun getSpendings() : Flowable<List<SpendingsResponse>>{
        return SpendingsService.getSpendings()
    }
    fun getReports() : Flowable<List<ReportsResponse>>{
        return SpendingsService.getReports()
    }
}