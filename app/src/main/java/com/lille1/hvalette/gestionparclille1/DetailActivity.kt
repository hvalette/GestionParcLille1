package com.lille1.hvalette.gestionparclille1

import android.location.Geocoder
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*
import android.content.Intent



class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = this.intent.extras.get("id")
        val probleme = GestionParcLille1App.database.problemeDao().getById(id.toString().toInt())
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT)
        when (probleme.type) {
            0 -> {
                typeImage.setImageResource(R.drawable.tree)
                type.text = "Arbre à tailler"
            }
            1 -> {
                typeImage.setImageResource(R.drawable.axe)
                type.text = "Arbre à couper"
            }
            2 -> {
                typeImage.setImageResource(R.drawable.garbage)
                type.text = "Détritus à ramasser"
            }
            3 -> {
                typeImage.setImageResource(R.drawable.bush)
                type.text = "Haie à tailler"
            }
            4 -> {
                typeImage.setImageResource(R.drawable.grass)
                type.text = "Mauvaises herbes à enlever"
            }
        }
        description.text = probleme.description
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(probleme.latitude, probleme.longitude, 1)
        addresse.text = addresses.get(0).getAddressLine(0)
        localiser.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:" + probleme.latitude + "," + probleme.longitude + "?q=" + addresses.get(0).getAddressLine(0))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        supprimer.setOnClickListener {
            GestionParcLille1App.database.problemeDao().delete(probleme)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}
