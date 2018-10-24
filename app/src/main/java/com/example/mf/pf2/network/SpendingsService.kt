package com.example.mf.pf2.network

import com.example.mf.pf2.ReportsResponse
import com.example.mf.pf2.SpendingsResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface SpendingsService {
    @GET("/spendings")
    fun getSpendings() : Flowable<List<SpendingsResponse>>

    @GET("/reports")
    fun getReports() : Flowable<List<ReportsResponse>>

}

