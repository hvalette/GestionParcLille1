package com.lille1.hvalette.gestionparclille1

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface ProblemeDao {

    @Query("SELECT * from problemes")
    fun getAll(): List<Probleme>

    @Insert
    fun insert(probleme: Probleme)

}