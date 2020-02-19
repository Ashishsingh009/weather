package com.ashish.weather.view

import android.graphics.Color
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.ashish.weather.R
import com.ashish.weather.databinding.ActivityGraphViewBinding
import com.ashish.weather.model.forecast.JSONForecast
import com.ashish.weather.util.Constant
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.model.GradientColor
import com.github.mikephil.charting.utils.MPPointF

/**
 * BarChartGraphActivity class is displaying the bar chart of forecastApi.
 * reference:https://github.com/PhilJay/MPAndroidChart
 * @author:Ashish_Singh
 *
 */

class BarChartGraphActivity : AppCompatActivity(), OnChartValueSelectedListener {
    private lateinit var bindingGraphViewBinding: ActivityGraphViewBinding
    private lateinit var jsonForecast: JSONForecast
    private val xAxisValues = ArrayList<String>()
    private val yAxisValues = ArrayList<BarEntry>()
    private lateinit var chart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGraphViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_graph_view)
        chart = bindingGraphViewBinding.barChart
        val bundle: Bundle? = intent.extras
        bundle.let {
            bundle?.apply {
                //Serializable Data
                jsonForecast = getSerializable(Constant.FORECAST_DATA) as JSONForecast
                run {
                    populateGraphData()

                }
            }
        }
    }


    private fun populateGraphData() {

        val barChartView = bindingGraphViewBinding.barChart

        val barWidth =0.15f

        for (i in 0 until jsonForecast.list.size){
            Constant.getMonthName(jsonForecast.list[i].dt.toLong())?.let { xAxisValues.add(it) }

            yAxisValues.add(BarEntry(i.toFloat(),floatArrayOf(jsonForecast.list[i].main.temp_max.toFloat(), jsonForecast.list[i].main.temp_min.toFloat())))
        }


        val  barDataSet1: BarDataSet?
        if (chart.data != null && chart.data.dataSetCount > 0) {
            chart.data.getDataSetByIndex(0) as BarDataSet

            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        }

            val startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
            val startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light)
            val startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
            val startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light)
            val startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light)
            val endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark)
            val endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple)
            val endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark)
            val endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark)
            val endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark)

            val gradientColors: MutableList<GradientColor> =
                java.util.ArrayList()
            gradientColors.add(GradientColor(startColor1, endColor1))
            gradientColors.add(GradientColor(startColor2, endColor2))
            gradientColors.add(GradientColor(startColor3, endColor3))
            gradientColors.add(GradientColor(startColor4, endColor4))
            gradientColors.add(GradientColor(startColor5, endColor5))





        barDataSet1 = BarDataSet(yAxisValues, getString(R.string.temp))
        barDataSet1.setColors(Color.BLUE, Color.RED)
        barDataSet1.label = "date"
        barDataSet1.setDrawIcons(false)
        barDataSet1.setDrawValues(false)
        barDataSet1.gradientColors = gradientColors




        val barData = BarData(barDataSet1)

        barChartView.description.isEnabled = false
        barChartView.description.textSize = 0f
        barData.setValueFormatter(LargeValueFormatter())
        barChartView.data = barData
        barChartView.barData.barWidth = barWidth
        barChartView.xAxis.axisMinimum = 0f
        barChartView.xAxis.axisMaximum = 12f
        barChartView.data.isHighlightEnabled = false
        barChartView.invalidate()

        val legend = barChartView.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.textSize = 11f

        val legendEntries = arrayListOf<LegendEntry>()

        legendEntries.add(LegendEntry("Date MMM dd hh", LegendForm.SQUARE, 8f, 8f, null, Color.RED))

        legend.setCustom(legendEntries)

        legend.yOffset = 8f
        legend.xOffset = 8f
        legend.yEntrySpace = 0f


        val xAxis = barChartView.xAxis
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setCenterAxisLabels(true)
        xAxis.setDrawGridLines(false)
        xAxis.textSize = 9f

        xAxis.position = XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)

        xAxis.labelCount = 12
        xAxis.mAxisMaximum = 12f
        xAxis.setCenterAxisLabels(true)
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.spaceMin = 4f
        xAxis.spaceMax = 4f

        barChartView.setVisibleXRangeMaximum(12f)
        barChartView.setVisibleXRangeMinimum(12f)
        barChartView.isDragEnabled = true

        //Y-axis
        barChartView.axisRight.isEnabled = false
        barChartView.setScaleEnabled(true)

        val leftAxis = barChartView.axisLeft
        leftAxis.valueFormatter = LargeValueFormatter()
        leftAxis.setDrawGridLines(false)
        leftAxis.spaceTop = 1f
        leftAxis.axisMinimum = 0f


        barChartView.data = barData
        barChartView.setVisibleXRange(1f, 12f)
    }
    override fun onNothingSelected() {
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e == null) return
        val onValueSelectedRectF = RectF()
        val bounds: RectF = onValueSelectedRectF
        chart.getBarBounds(e as BarEntry?, bounds)
        val position = chart.getPosition(e, AxisDependency.LEFT)

        Log.i("bounds", bounds.toString())
        Log.i("position", position.toString())

        Log.i(
            "x-index",
            "low: " + chart.lowestVisibleX + ", high: "
                    + chart.highestVisibleX
        )

        MPPointF.recycleInstance(position)
    }
}