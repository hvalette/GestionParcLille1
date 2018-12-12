package com.lille1.hvalette.gestionparclille1.activity

import android.Manifest
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.lille1.hvalette.gestionparclille1.GPSLocation
import com.lille1.hvalette.gestionparclille1.R
import com.lille1.hvalette.gestionparclille1.activity.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {


    private lateinit var locationManager : LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 1)
            checkLocationPermission()
            return
        }else{
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_FINE
            GPSLocation.instance.setLatitude(locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true)).latitude)
            GPSLocation.instance.setLongitude(locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true)).longitude)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, GPSLocation.instance)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, GPSLocation.instance)
        }
    }

    override fun onBackPressed() {
    }

}
