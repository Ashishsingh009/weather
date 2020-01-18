package com.ashish.weather.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.util.*


class Utilities {

    companion object {
        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
                return getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                } ?: false
            }
        }

        @JvmStatic
        fun isLocationEnabled(context: Context): Boolean {
            var locationManager: LocationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        }

        @JvmStatic
        fun getPinCode(context: Context, latitude: Double, longitude: Double): String {
            val geoCoder = Geocoder(context, Locale.getDefault())
            val addresses: List<Address> = geoCoder.getFromLocation(latitude, longitude, 1)


            return addresses[0].adminArea
        }
    }
}