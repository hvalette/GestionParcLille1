package com.lille1.hvalette.gestionparclille1

import android.app.Application
import android.arch.persistence.room.Room

class GestionParcLille1App() : Application() {

    companion object {
        lateinit var database: GestionParcLille1Database
    }

    override fun onCreate() {
        super.onCreate()
        GestionParcLille1App.database = Room.databaseBuilder(this, GestionParcLille1Database::class.java, "problemes.db").allowMainThreadQueries().build()
    }
}