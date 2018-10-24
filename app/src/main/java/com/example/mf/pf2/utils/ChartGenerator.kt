package com.example.mf.pf2.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

@Suppress("CanBeVal")
class ChartGenerator : IChartGenerator {

    private fun generateEntries(dataset: List<Int>) : ArrayList<Entry>{
        var entries : ArrayList<Entry> = ArrayList()
        for (index in 0 until dataset.size) entries.add(Entry(index.toFloat(),dataset[index].toFloat()))
        return entries
    }
    fun generateLabels(size: Int, label: String) : Array<String>{
        if (size <= 0) throw IllegalArgumentException("Label size must be at least 1")
        val labels = ArrayList<String>()
//        dataset.forEachIndexed { index, _ -> labels.add("$label ${index+1}") }
        for (index in 0 until size) labels.add("$label ${index+1}")
        return labels.toTypedArray()

    }

    override fun makeLineChart(
            dataset: List<Int>,
            labels: Array<String>,
            chart: LineChart,
            chart_name: String,
            drawable: Drawable
    ){

        val lineDataSet = LineDataSet(generateEntries(dataset),chart_name)
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = Color.CYAN
        val lineData = LineData(lineDataSet)

        if (dataset.size == labels.size){
            val formatter = IAxisValueFormatter { value, axis -> labels[value.toInt()] }
            val xAxis = chart.xAxis
            xAxis.granularity = 1f
            xAxis.valueFormatter = formatter
        }

        chart.data = lineData
        chart.invalidate()
    }
    //TODO : CREATE CHART SETTINGS INTERFACE
    fun makeLineChart(
            datasets: List<List<Int>>,
            labels: Array<String>,
            chart: LineChart,
            chart_names: Array<String>
    ){
        if (datasets.size < chart_names.size)
            throw IllegalArgumentException("Not enough chart names to make chart")


        var composite : ArrayList<ILineDataSet> = ArrayList()
        val templateColors = Constants.mockColors
        for ((i, dataset) in datasets.withIndex()){
            var lineDataSet = LineDataSet(generateEntries(dataset), chart_names[i])
            var templateDrawable = GradientDrawable()
            templateDrawable.apply {
                orientation = GradientDrawable.Orientation.TOP_BOTTOM
                colors = arrayOf(templateColors[i%templateColors.size],Color.TRANSPARENT).toIntArray()
                cornerRadius = 0f
            }


            lineDataSet.apply {
                color = templateColors[i%templateColors.size]
                fillDrawable = templateDrawable
                setDrawFilled(true)
                setDrawValues(false)
                setCircleColor(color)
                valueTextColor = Color.WHITE
                valueTextSize = 13f
                lineWidth = 1.5f
                highLightColor = Color.WHITE
            }
            composite.add(lineDataSet)
        }

        var filtered = datasets.filter { it -> it.size != labels.size }
        if (filtered.isEmpty()){
            val formatter = IAxisValueFormatter { value, _ -> labels[value.toInt()] }
            val xAxis = chart.xAxis
            xAxis.granularity = 1f
            xAxis.valueFormatter = formatter
            xAxis.textColor = Color.WHITE
            xAxis.textSize = 10f
        }

        val lineData = LineData(composite)
        lineData.apply {
            setValueTextColor(Color.WHITE)
        }
        chart.apply {

            setBorderColor(Color.WHITE)
            setGridBackgroundColor(Color.WHITE)

            data = lineData
            legend.textColor = Color.WHITE
            description.text = ""

            axisLeft.isEnabled = false
            axisRight.textColor  = Color.WHITE
            axisRight.textSize = 12f
            axisRight.gridColor = Color.WHITE
            xAxis.gridColor = Color.WHITE
            xAxis.axisLineColor = Color.WHITE
            invalidate()
        }

    }


}