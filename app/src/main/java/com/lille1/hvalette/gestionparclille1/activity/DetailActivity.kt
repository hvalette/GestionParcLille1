package com.lille1.hvalette.gestionparclille1.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.location.Geocoder
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*
import android.content.Intent
import com.lille1.hvalette.gestionparclille1.GestionParcLille1App
import com.lille1.hvalette.gestionparclille1.R
import com.lille1.hvalette.gestionparclille1.entity.Probleme


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = this.intent.extras.get("id")
        val probleme = GestionParcLille1App.database.problemeDao().getById(id.toString().toInt())

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
        addresse.text = probleme.adresse

        localiser.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:" + probleme.latitude + "," + probleme.longitude + "?q=" + probleme.adresse)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        supprimer.setOnClickListener {
            deleteConfirmation(probleme)

        }

    }

    /**
     * Méthode qui montre une modale avant de supprimer le probleme
     */
    private fun deleteConfirmation(probleme: Probleme){
        lateinit var dialog:AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Supprimer le problème")
        builder.setMessage("Etes vous sûr de vouloir supprimer ce problème ?")


        // On click listener for dialog buttons
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    GestionParcLille1App.database.problemeDao().delete(probleme)
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        builder.setPositiveButton("Supprimer",dialogClickListener)
        builder.setNegativeButton("Annuler",dialogClickListener)
        dialog = builder.create()
        dialog.show()
    }
}
