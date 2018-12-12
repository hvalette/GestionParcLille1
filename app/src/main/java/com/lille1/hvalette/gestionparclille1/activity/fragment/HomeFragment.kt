package com.lille1.hvalette.gestionparclille1.activity.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lille1.hvalette.gestionparclille1.*
import kotlinx.android.synthetic.main.home_fragment.*
import android.view.*
import com.lille1.hvalette.gestionparclille1.activity.AddProblemeActivity
import com.lille1.hvalette.gestionparclille1.activity.HomeActivity
import com.lille1.hvalette.gestionparclille1.activity.viewmodel.HomeViewModel
import com.lille1.hvalette.gestionparclille1.adapter.GestionAdapter
import com.lille1.hvalette.gestionparclille1.entity.Probleme
import java.util.*


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val myDataset = GestionParcLille1App.database.problemeDao().getAll()
        viewManager = LinearLayoutManager(this.context)
        viewAdapter = GestionAdapter(myDataset, this.context)
        recyclerView = problemes_recycler_view
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

        fab.setOnClickListener {
            val intent = Intent(context, AddProblemeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.delete -> {
                GestionParcLille1App.database.problemeDao().deleteAll()
                val intent = Intent(this.context, HomeActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.init -> {
                val centerLat = 50.609213
                val centerLong = 3.141944
                for (i in 0..10){
                    var randomLat = Random().nextInt(5000)
                    var randomLong = Random().nextInt(5000)
                    var lat = centerLat*1000000
                    var long = centerLong*1000000
                    if(Random().nextInt(1) == 1){
                        lat += randomLat
                    }else{
                        lat -= randomLat
                    }
                    if(Random().nextInt(1) == 1){
                        long += randomLong
                    }else{
                        long -= randomLong
                    }
                    lat /= 1000000
                    long /= 1000000

                    var geocoder = Geocoder(this.context)
                    var adresse = geocoder.getFromLocation(lat, long,1)[0].getAddressLine(0);

                    GestionParcLille1App.database.problemeDao().insert(
                    Probleme(
                        Random().nextInt(5),
                        lat,
                        long,
                        adresse,
                        "Description à écrire"
                    )
                )
                }
                val intent = Intent(this.context, HomeActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }

}
