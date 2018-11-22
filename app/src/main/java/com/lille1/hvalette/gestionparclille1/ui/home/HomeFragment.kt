package com.lille1.hvalette.gestionparclille1.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lille1.hvalette.gestionparclille1.*
import kotlinx.android.synthetic.main.home_fragment.*
import android.view.*


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
        // TODO: Use the ViewModel
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
                GestionParcLille1App.database.problemeDao().insert(Probleme(0,50.6357258,3.0490061,"Une branche gêne la route."))
                GestionParcLille1App.database.problemeDao().insert(Probleme(1,50.6376511,3.1499041,"Un arbre à poussé ici cette nuit."))
                GestionParcLille1App.database.problemeDao().insert(Probleme(2,48.8583736,2.2922926,"Il faut vider les poubelles avant que les touristes n'arrivent."))
                GestionParcLille1App.database.problemeDao().insert(Probleme(3,48.8030847,2.1252058,"Il faut tailler les haies du jardin !"))
                GestionParcLille1App.database.problemeDao().insert(Probleme(4,47.6161296,1.5150293,"Au plus vite, cette plante se propage en quelques jours"))
                val intent = Intent(this.context, HomeActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }

}
