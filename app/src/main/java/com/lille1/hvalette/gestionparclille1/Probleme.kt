package com.lille1.hvalette.gestionparclille1

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "problemes")
data class Probleme(var type: Int, var latitude: Double, var longitude: Double, var description: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor():this(0,0.0,0.0, "")
}