package  com.ashish.weather.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashish.weather.R
import com.ashish.weather.WeatherApp
import com.ashish.weather.apis.WeatherApi
import com.ashish.weather.databinding.ActivityMainBinding
import com.ashish.weather.di.component.DaggerWeatherMainComponent
import com.ashish.weather.listener.OnScreenRefreshListener
import com.ashish.weather.model.JSONWeatherSet
import com.ashish.weather.model.forecast.JSONForecast
import com.ashish.weather.util.Constant
import com.ashish.weather.util.Utilities
import com.ashish.weather.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.Serializable
import javax.inject.Inject

/**
 * MainActivity class is the launcher class for this app, is
 *
 */

class MainActivity : AppCompatActivity(), OnScreenRefreshListener, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val TAG: String = MainActivity::class.java.name
    private lateinit var jsonWeatherSet: JSONWeatherSet
    private lateinit var jsonForecast: JSONForecast

    @JvmField
    @Inject
    var mRetrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getToolBar()

        createViewModel()
        attachObservers()
        if (Utilities.isNetworkAvailable(this)) {
            viewModel.getWeatherData
        } else {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.network_error),
                Toast.LENGTH_LONG
            )
                .show()
        }

    }

    private fun initializeInjector() {
        DaggerWeatherMainComponent
            .builder()
            .appComponent(WeatherApp.mApp?.getAppComponent())
            .build()
            .inject(this)
    }

    /**
     * Method to set ToolBar tittle.
     */
    private fun getToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = resources.getString(R.string.app_name)
    }

    private fun createViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onRefresh() {
    }

    private fun attachObservers() {

        viewModel.loading?.observe(this, Observer<Boolean> { aBoolean ->
            //binding.swipeRefresh.isRefreshing = !aBoolean
        })
        viewModel.apiError?.observe(
            this,
            Observer<Throwable?> { throwable ->
                if (throwable is Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.network_error),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return@Observer
                }
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.network_error),
                    Toast.LENGTH_LONG
                ).show()
            })

        viewModel.weatherResponse?.observe(this, Observer {
            it?.let {
                binding.weatherSet = it
                jsonWeatherSet = it
                binding.datetime.text =
                    getString(R.string.last_update_str) + "\n" + Constant.getTimeMilliSec((it.dt!!).toLong())
            }

        })
    }


    private fun getGraphForeCastData() {
        val weatherApi = mRetrofit!!.create(WeatherApi::class.java)!!
        weatherApi.getWeatherForecast("dubai,uae", Constant.METRIC, Constant.API_KEY)
            ?.enqueue(object : Callback<JSONForecast?> {
                override fun onResponse(call: Call<JSONForecast?>, response: Response<JSONForecast?>) {
                    var intent = Intent(this@MainActivity, GraphViewActivity::class.java)
                    jsonForecast= response.body()!!
                    intent.putExtra(Constant.FORECAST_DATA, jsonForecast as Serializable)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<JSONForecast?>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.network_error),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.menu_forecast) {
            getGraphForeCastData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.detailsTxtView) {
            jsonWeatherSet?.let {
                var intent = Intent(this@MainActivity, WeatherDetailActivity::class.java)
                intent.putExtra(Constant.PARSE_DATA, jsonWeatherSet as Serializable)
                startActivity(intent)
            }

        }
    }

}
