package com.lille1.hvalette.gestionparclille1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_probleme.*

class AddProblemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_probleme)
        //var dao = GestionParcLille1App.database.problemeDao()
        submit.setOnClickListener{
            //dao.insert(Probleme(type.text.toString().toInt(), latitude.text.toString().toDouble(), longitude.text.toString().toDouble(), description.text.toString()))
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
