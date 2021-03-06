package com.lille1.hvalette.gestionparclille1.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Entité probleme caractérisée par un type, des coordonnées gps, une adresse et une description
 */
@Entity(tableName = "problemes")
data class Probleme(var type: Int, var latitude: Double, var longitude: Double, var adresse: String, var description: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor():this(0,0.0,0.0, "", "")
}