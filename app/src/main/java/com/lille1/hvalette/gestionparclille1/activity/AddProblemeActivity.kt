package com.lille1.hvalette.gestionparclille1.activity

import android.content.Intent
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.lille1.hvalette.gestionparclille1.GPSLocation
import com.lille1.hvalette.gestionparclille1.GestionParcLille1App
import com.lille1.hvalette.gestionparclille1.R
import com.lille1.hvalette.gestionparclille1.entity.Probleme
import kotlinx.android.synthetic.main.activity_add_probleme.*

class AddProblemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_probleme)

        // initialise le selecteur pour le type de probleme
        val myStrings = arrayOf("Arbre à tailler", "Arbre à couper", "Détritus à ramasser", "Haie à tailler", "Mauvaises herbes à enlever", "Autre")
        type.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)

        val dao = GestionParcLille1App.database.problemeDao()

        var latitude = GPSLocation.instance.getLatitude()
        var longitude = GPSLocation.instance.getLongitude()

        //recupere l'adresse à partir de la latitude et longitude
        val geocoder = Geocoder(this)
        var adresses = geocoder.getFromLocation(latitude,longitude,1)
        if(!adresses.isEmpty()) {
            adresse.setText(adresses[0].getAddressLine(0))
        }

        //ajoute le probleme lorque l'on clique sur le bouton
        submit.setOnClickListener{
            val probleme = Probleme(
                type.selectedItemPosition,
                GPSLocation.instance.getLatitude(),
                GPSLocation.instance.getLongitude(),
                adresse.text.toString(),
                description.text.toString()
            )

            dao.insert(probleme)

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
