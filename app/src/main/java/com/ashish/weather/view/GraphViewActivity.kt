package com.ashish.weather.view

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ashish.weather.R
import com.ashish.weather.databinding.ActivityGraphViewBinding
import com.ashish.weather.model.ChartData
import com.ashish.weather.model.forecast.JSONForecast
import com.ashish.weather.util.Constant
import com.ashish.weather.util.CustomMarkerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class GraphViewActivity : AppCompatActivity(), OnChartValueSelectedListener {
    private lateinit var bindingGraphViewBinding: ActivityGraphViewBinding
    private lateinit var jsonForecast: JSONForecast
    private val xAxisValues = ArrayList<String>()
    private val yAxisValues = ArrayList<String>()
    private lateinit var chart: LineChart

    private lateinit var barChart: BarChart
    //    private  var data: MutableList<ChartData>?=null
    private lateinit var customMarkerView: CustomMarkerView
    private var data: MutableList<ChartData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGraphViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_graph_view)
        barChart = bindingGraphViewBinding.barChart
        val bundle: Bundle? = intent.extras
        bundle.let {
            bundle?.apply {
                //Serializable Data
                jsonForecast = getSerializable(Constant.FORECAST_DATA) as JSONForecast
                run {
                    setData(jsonForecast)

                }
            }
        }
    }

    private fun setData(jsonForecast: JSONForecast) {
        val list = jsonForecast.list

        for (i in 0 until list.size) {
            var chartData = ChartData(list[i].main.temp.toString(), list[i].dt_txt)
            data.let { it.add(chartData) }


        }
        drawGraph()
    }

    override fun onNothingSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun getValueFormatter(xVals: ArrayList<String>): IAxisValueFormatter {
        return IAxisValueFormatter { value, _ ->
            (value.toInt()).toString()
        }
    }

    private fun drawGraph() {


        customMarkerView = CustomMarkerView(this, R.layout.custom_marker_view)
        chart.run {
            setExtraOffsets(10f, 20f, 10f, 20f)
            setTouchEnabled(true)
            isDragEnabled = true
            setPinchZoom(false)
            marker = customMarkerView
            setBackgroundColor(Color.WHITE)
            setDrawGridBackground(false)
            isDragEnabled = true
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 1f


            setOnChartValueSelectedListener(this@GraphViewActivity)




            chart.description.isEnabled = false

            // if more than 60 entries are displayed in the chart, no values will be
            // drawn
            // if more than 60 entries are displayed in the chart, no values will be
// drawn
            chart.setMaxVisibleValueCount(60)

            // scaling can now only be done on x- and y-axis separately
            // scaling can now only be done on x- and y-axis separately
            chart.setPinchZoom(false)

            chart.setDrawGridBackground(false)

            description.isEnabled = false

            xAxis.gridColor = Color.GRAY
            xAxis.setDrawAxisLine(true)
            xAxis.axisLineWidth = 2f
            xAxis.setDrawGridLines(false)

            axisRight.isEnabled = false
            axisLeft.isEnabled = true
            axisLeft.setDrawGridLines(true)
            axisLeft.setDrawAxisLine(true)
            axisLeft.gridColor = Color.GRAY
            axisLeft.axisLineWidth = 2f
            legend.isEnabled = false
            animateXY(500, 500)
            // don't forget to refresh the drawing
            invalidate()
        }

        // graph's data
        val values = ArrayList<Entry>()
        if (data.size > 0) {
            for (i in 0 until (data as List<ChartData>).size) {
                val xValue: String = data[i].date
                val yValue: String = data[i].temp
                xAxisValues.add(data[i].date)
                values.add(Entry(xValue.toFloat(), yValue.toFloat()))

            }
        }

        // set graph's x-Axis properties
        val xAxis = chart.xAxis
        xAxis.run {
            textColor = Color.GRAY
            position = XAxis.XAxisPosition.BOTTOM
            setLabelCount(data.size, false)
            valueFormatter = getValueFormatter(xAxisValues) as ValueFormatter?

        }


        // set graph's y-Axis properties
        val y = chart.axisLeft
        y.run {
            textColor = Color.GRAY
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            setDrawGridLines(true)
            setLabelCount(data!!.size, false)
            setData(values)
        }
    }

    private fun setData(values: List<Entry>) {
        val lineDataSet: LineDataSet

        if (chart.data != null && chart.data.dataSetCount > 0) {
            lineDataSet = chart.data.getDataSetByIndex(0) as LineDataSet
            lineDataSet.values = values
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {


            lineDataSet = LineDataSet(values, "")
            lineDataSet.setDrawIcons(false)
            lineDataSet.color =
                Color.parseColor("#1A529C")//ContextCompat.getColor(context, R.color.journey_blue)
            lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            lineDataSet.lineWidth = 3f
            lineDataSet.circleRadius = 0f
            lineDataSet.axisDependency = YAxis.AxisDependency.LEFT
            lineDataSet.valueTextSize = 13f
            lineDataSet.highLightColor = Color.GRAY
            lineDataSet.setDrawFilled(true)
            lineDataSet.formLineWidth = 2f
            lineDataSet.formLineDashEffect = DashPathEffect(floatArrayOf(20f, 15f), 0f)
            lineDataSet.formSize = 18f
            lineDataSet.setDrawValues(false)
            lineDataSet.fillColor = Color.WHITE
            lineDataSet.label
            lineDataSet.circleHoleColor = Color.TRANSPARENT
            lineDataSet.setDrawCircleHole(false)// = Color.TRANSPARENT
            lineDataSet.setCircleColor(Color.TRANSPARENT)
            lineDataSet.valueTextColor = Color.GRAY
            lineDataSet.valueTextSize = 10f

            lineDataSet.setDrawVerticalHighlightIndicator(true)
            lineDataSet.setDrawHorizontalHighlightIndicator(false)

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)
            val data = LineData(dataSets)
            chart.data = data


        }
    }


}
