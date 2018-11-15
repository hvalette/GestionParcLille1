package com.lille1.hvalette.gestionparclille1

import android.arch.persistence.room.*

@Dao
interface ProblemeDao {

    @Query("SELECT * from problemes")
    fun getAll(): List<Probleme>

    @Query("SELECT * FROM problemes WHERE id = :id ")
    fun getById(id:Int): Probleme

    @Insert
    fun insert(probleme: Probleme)

    @Delete
    fun delete(probleme: Probleme)

}