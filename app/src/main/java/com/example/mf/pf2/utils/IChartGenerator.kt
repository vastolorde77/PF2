package com.example.mf.pf2.utils

import android.graphics.drawable.Drawable
import com.github.mikephil.charting.charts.LineChart

interface IChartGenerator{
    fun makeLineChart(dataset: List<Int>, labels: Array<String>, chart: LineChart, chart_name: String,drawable: Drawable)
}