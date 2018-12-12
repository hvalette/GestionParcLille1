package com.lille1.hvalette.gestionparclille1

import android.app.Application
import android.arch.persistence.room.Room

/**
 * Classe principale de l'application
 */
class GestionParcLille1App() : Application() {

    /**
     * Permet d'obternie la base de donnée sous forme de singleton
     */
    companion object {
        lateinit var database: GestionParcLille1Database
    }

    override fun onCreate() {
        super.onCreate()
        GestionParcLille1App.database = Room.databaseBuilder(this, GestionParcLille1Database::class.java, "problemes.db").allowMainThreadQueries().build()
    }
}