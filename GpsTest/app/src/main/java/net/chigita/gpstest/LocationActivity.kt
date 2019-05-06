package net.chigita.gpstest

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import net.chigita.gpstest.databinding.ActivityLocationBinding

/**
 * Created by chigichan24 on 2019-04-23.
 */

class LocationActivity : AppCompatActivity(), LocationListener {

  companion object {
    const val WIFI = 0
    const val GPS = 1
  }

  private val binding by lazy {
    DataBindingUtil.setContentView<ActivityLocationBinding>(
        this,
        R.layout.activity_location
    )
  }

  private lateinit var locationManager: LocationManager
  private var mLocationType: Int? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    binding.buttonGps.setOnClickListener {
      mLocationType = GPS
      Log.d("test", mLocationType.toString())
      if (ActivityCompat.checkSelfPermission(this,
              Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
          ActivityCompat.checkSelfPermission(this,
              Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return@setOnClickListener
      }
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.0f, this)
    }
    binding.buttonWifi.setOnClickListener {
      mLocationType = WIFI
      Log.d("test", mLocationType.toString())
      if (ActivityCompat.checkSelfPermission(this,
              Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
          ActivityCompat.checkSelfPermission(this,
              Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return@setOnClickListener
      }
      locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.0f, this)
    }
  }

  override fun onPause() {
    super.onPause()
    locationManager.let {
      if (ActivityCompat.checkSelfPermission(this,
              Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
          ActivityCompat.checkSelfPermission(this,
              Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return@let
      }
      locationManager.removeUpdates(this)
    }
  }

  override fun onLocationChanged(location: Location?) {
    Log.d("test", "location changed")
    if (mLocationType == null) return
    if (mLocationType == GPS) {
      binding.textGpsLatitude.text = location?.latitude.toString()
      binding.textGpsLongitude.text = location?.longitude.toString()
      binding.textGpsAccuracy.text = location?.accuracy.toString()
      binding.textGpsAltitude.text = location?.altitude.toString()
    } else if (mLocationType == WIFI) {
      binding.textWifiLatitude.text = location?.latitude.toString()
      binding.textWifiLongitude.text = location?.longitude.toString()
      binding.textWifiAccuracy.text = location?.accuracy.toString()
      binding.textWifiAltitude.text = location?.altitude.toString()
    }
    if (ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      locationManager.removeUpdates(this)
    }
  }

  override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
  }

  override fun onProviderEnabled(provider: String?) {
  }

  override fun onProviderDisabled(provider: String?) {
  }
}