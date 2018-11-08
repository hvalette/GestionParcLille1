package com.lille1.hvalette.gestionparclille1

import android.content.Context
import android.location.Address
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.probleme_view.view.*
import android.location.Geocoder
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
                                    viewType: Int): GestionAdapter.MyViewHolder {
        // create a new view
        val listView = LayoutInflater.from(parent.context)
            .inflate(R.layout.probleme_view, parent, false)
        return MyViewHolder(listView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val addresses: List<Address>
        val geocoder = Geocoder(context, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            myDataset[position].lat,
            myDataset[position].long,
            1
        )

        if(addresses != null && addresses.isNotEmpty()) {
            holder.listView.latitude.text = addresses[0].getAddressLine(0)
        }else {
            holder.listView.latitude.text = myDataset[position].lat.toString()
            holder.listView.longitude.text = myDataset[position].long.toString()
        }

        holder.listView.item_id.text = myDataset[position].id.toString()
        holder.listView.longitude.text = myDataset[position].long.toString()
        holder.listView.description.text = myDataset[position].description
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
            4 -> {
                holder.listView.probleme_image.setImageResource(R.drawable.bush)
                holder.listView.type.text = "Haie à tailler"
            }
            5 -> {
                holder.listView.probleme_image.setImageResource(R.drawable.grass)
                holder.listView.type.text = "Mauvaises herbes à enlever"
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}