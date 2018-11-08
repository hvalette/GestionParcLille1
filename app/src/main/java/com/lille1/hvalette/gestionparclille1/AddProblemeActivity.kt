package com.lille1.hvalette.gestionparclille1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add_probleme.*

class AddProblemeActivity : AppCompatActivity() {

    private var locationManager : LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_probleme)
        // Create persistent LocationManager reference
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }else{
            val myLocation = locationManager?.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
            latitude.setText(myLocation?.latitude.toString())
            latitude.setText(myLocation?.longitude.toString())
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener);
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        }

        val myStrings = arrayOf("Arbre à tailler", "Arbre à couper", "Détritus à ramasser", "Haie à tailler", "Mauvaises herbes à enlever", "Autre")
    System.out.println("-122.555".toDouble())
        type.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
        var dao = GestionParcLille1App.database.problemeDao()
        submit.setOnClickListener{
            dao.insert(Probleme(type.selectedItemPosition, latitude.text.toString().toDouble(), longitude.text.toString().toDouble(), description.text.toString()))
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            latitude.setText(location.latitude.toString())
            longitude.setText(location.longitude.toString())
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}
