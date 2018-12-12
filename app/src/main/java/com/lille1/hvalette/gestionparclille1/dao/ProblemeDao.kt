package com.lille1.hvalette.gestionparclille1.dao

import android.arch.persistence.room.*
import com.lille1.hvalette.gestionparclille1.entity.Probleme

/**
 * Permet d'effectuer des requetes à la base de donnée
 */
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

    @Query("DELETE FROM problemes")
    fun deleteAll()

}