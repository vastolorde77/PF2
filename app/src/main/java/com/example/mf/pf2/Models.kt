package com.example.mf.pf2

import com.squareup.moshi.Json
import java.util.*


data class Spendings(
        val type: String = "",
        val amount: Int = 0,
        val product: String = "No Product",
        val date: Date = Date(),
        val location: Pair<Double, Double> = Pair(0.toDouble(), 0.toDouble())
)

data class SpendingsResponse(
        @field:Json(name = "type") val type: String,
        @field:Json(name = "amount") val amount: Int,
        @field:Json(name = "location") val location: Int,
        @field:Json(name = "product") val product: String,
        @field:Json(name = "date_created") val date: Long
)

data class ReportsResponse(
        @field:Json(name = "report") val reports: List<Int>
)