package com.example.mf.pf2.network

import com.example.mf.pf2.ReportsResponse
import com.example.mf.pf2.SpendingsResponse
import io.reactivex.Flowable
import retrofit2.http.*

interface SpendingsService {
    @GET("/spendings")
    fun getSpendings(
            @Query("sortBy") sortBy: String = "createdAt",
            @Query("order") order: String = "desc"
    ): Flowable<List<SpendingsResponse>>

    @GET("/reports")
    fun getReports(): Flowable<List<ReportsResponse>>

    @FormUrlEncoded
    @POST("/spendings")
    fun postSpending(@Field("type") type: String, @Field("amount") amount: Int): Flowable<SpendingsResponse>

}

