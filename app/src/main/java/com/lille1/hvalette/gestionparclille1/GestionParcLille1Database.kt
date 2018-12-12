package com.lille1.hvalette.gestionparclille1

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.lille1.hvalette.gestionparclille1.dao.ProblemeDao
import com.lille1.hvalette.gestionparclille1.entity.Probleme

/**
 * Initialise la base de donnée Room
 */
@Database(entities = arrayOf(Probleme::class), version = 1,  exportSchema = false)
abstract class GestionParcLille1Database : RoomDatabase() {

    abstract fun problemeDao(): ProblemeDao
}