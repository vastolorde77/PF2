package com.example.mf.pf2.utils

import android.graphics.Color
import com.example.mf.pf2.R

object Constants {
    val mockThumbnails = arrayOf(R.drawable.ic_credit_cardfg, R.drawable.ic_moviesfg, R.drawable.ic_storefg)
    val mockDataset = listOf(
    listOf(13000, 12500, 13200, 13500, 13100, 13800, 14500),
    listOf(12500, 12000, 13000, 13200, 13800, 14200, 13500),
    listOf(13200, 13500, 14200, 13500, 12500, 12000, 13000)
    )
    val mockColors =  mutableListOf(
    Color.rgb(228, 87, 46),
    Color.rgb(61, 165, 217),
    Color.rgb(255, 201, 20)
    )
    val mockLabels = arrayOf("Mon.", "Tu.", "Wed.", "Th.", "Fri.","Sat.","Sun.")
}