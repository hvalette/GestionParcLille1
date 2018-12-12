package com.lille1.hvalette.gestionparclille1.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.probleme_view.view.*
import android.location.Geocoder
import android.widget.Toast
import com.lille1.hvalette.gestionparclille1.R
import com.lille1.hvalette.gestionparclille1.activity.DetailActivity
import com.lille1.hvalette.gestionparclille1.entity.Probleme
import java.util.*


class GestionAdapter(private val myDataset: List<Probleme>, private val context : Context?) :
    RecyclerView.Adapter<GestionAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val listView: View) : RecyclerView.ViewHolder(listView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        // create a new view
        val listView = LayoutInflater.from(parent.context)
            .inflate(R.layout.probleme_view, parent, false)
        return MyViewHolder(listView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.listView.latitude.text = myDataset[position].adresse


        when (myDataset[position].type) {
            0 -> {
                holder.listView.probleme_image.setImageResource(R.drawable.tree)
                holder.listView.type.text = "Arbre à tailler"
            }
            1 -> {
                holder.listView.probleme_image.setImageResource(R.drawable.axe)
                holder.listView.type.text = "Arbre à couper"
            }
            2 -> {
                holder.listView.probleme_image.setImageResource(R.drawable.garbage)
                holder.listView.type.text = "Détritus à ramasser"
            }
            3 -> {
                holder.listView.probleme_image.setImageResource(R.drawable.bush)
                holder.listView.type.text = "Haie à tailler"
            }
            4 -> {
                holder.listView.probleme_image.setImageResource(R.drawable.grass)
                holder.listView.type.text = "Mauvaises herbes à enlever"
            }
        }
        holder.listView.setOnClickListener {
            val intent = Intent(this.context, DetailActivity::class.java)
            intent.putExtra("id", myDataset[position].id)
            Toast.makeText(this.context, "" + myDataset[position].id, Toast.LENGTH_SHORT)
            if(this.context != null) {
                this.context.startActivity(intent)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}