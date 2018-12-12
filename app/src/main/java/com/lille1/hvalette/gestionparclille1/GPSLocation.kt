package com.lille1.hvalette.gestionparclille1

import android.location.Location
import android.location.LocationListener
import android.os.Bundle

/**
 * Cette classe permet de récupérer la latitude et longitude de l'utilisateur
 */
class GPSLocation : LocationListener {

    private var latitude : Double = 0.0
    private var longitude : Double = 0.0

    /**
     * Permet de creer un singleton de cette classe
     */
    companion object {
        val instance = GPSLocation()
    }

    override fun onLocationChanged(location: Location) {
        latitude = location.latitude
        longitude = location.longitude
    }
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}

    fun getLatitude(): Double {
        return latitude
    }

    fun getLongitude(): Double {
        return longitude
    }

    fun setLatitude(lat: Double){
        latitude = lat
    }

    fun setLongitude(long: Double){
        longitude = long
    }
}