package com.ashish.weather.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ashish.weather.R
import com.ashish.weather.databinding.ActivityWeatherDetailsBinding
import com.ashish.weather.model.JSONWeatherSet
import com.ashish.weather.util.Constant
import java.io.Serializable

class WeatherDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityWeatherDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_details)
        val bundle: Bundle? = intent.extras
        bundle.let {
            bundle?.apply {
                //Serializable Data
                val jsonWeatherSet = getSerializable(Constant.PARSE_DATA) as JSONWeatherSet?
                if (jsonWeatherSet != null) {
                    binding.weatherSet = jsonWeatherSet
                }
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id==R.id.graph_chart){
            var intent = Intent(this@WeatherDetailActivity, GraphViewActivity::class.java)
            startActivity(intent)
        }
    }
}