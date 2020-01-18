package  com.ashish.weather.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.test.espresso.idling.CountingIdlingResource
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
import com.ashish.weather.util.Utilities.Companion.isLocationEnabled
import com.ashish.weather.viewmodel.MainViewModel
import com.google.android.gms.location.*
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
    private val permissionId = 42
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mAddress: String
    lateinit var countingIdlingResource: CountingIdlingResource
    @JvmField
    @Inject
    var mRetrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
        countingIdlingResource = CountingIdlingResource(TAG)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getToolBar()

        createViewModel()
        attachObservers()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()


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

            }

        })
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled(this)) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        if (Utilities.isNetworkAvailable(this)) {
                            mAddress = Utilities.getPinCode(
                                this@MainActivity,
                                location.latitude,
                                location.longitude
                            )
                            viewModel.pinCode = mAddress
                            countingIdlingResource.increment()
                            viewModel.getWeatherData
                            countingIdlingResource.decrement()

                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                getString(R.string.network_error),
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }


                    }
                }
            } else {

                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            mAddress = Utilities.getPinCode(
                this@MainActivity,
                mLastLocation.latitude,
                mLastLocation.longitude
            )

        }
    }

    private fun getGraphForeCastData() {
        val weatherApi = mRetrofit!!.create(WeatherApi::class.java)!!
        weatherApi.getWeatherForecast(mAddress, Constant.METRIC, Constant.API_KEY)
            ?.enqueue(object : Callback<JSONForecast?> {
                override fun onResponse(
                    call: Call<JSONForecast?>,
                    response: Response<JSONForecast?>
                ) {
                    val intent = Intent(this@MainActivity, BarChartGraphActivity::class.java)
                    jsonForecast = response.body()!!
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
//        countingIdlingResource.decrement()
        if (id == R.id.menu_forecast) {

            getGraphForeCastData()


            return true
        } else if (id == R.id.refresh_menu) {
            viewModel.getWeatherData
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
            jsonWeatherSet.let {
                countingIdlingResource.decrement()
                val intent = Intent(this@MainActivity, WeatherDetailActivity::class.java)
                intent.putExtra(Constant.PARSE_DATA, jsonWeatherSet as Serializable)
                startActivity(intent)
            }

        }
    }

}
