package com.ashish.weather.util

import android.content.Context
import android.widget.TextView
import com.ashish.weather.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight


class CustomMarkerView : MarkerView {

    private var markerTextView:TextView = findViewById(R.id.tvContent)

    constructor(context: Context,layoutId:Int) : super(context,layoutId)

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e != null) {
            markerTextView.text=e.y.toString()
        }
    }

    override fun getY(): Float {
        return -height.toFloat()
    }

    override fun getX(): Float {
        return -(width/2).toFloat()
    }
}