package org.wit.placemark.views.placemarklist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import org.wit.placemark.R
import org.wit.placemark.adapters.PlacemarkAdapter
import org.wit.placemark.adapters.PlacemarkListener
import org.wit.placemark.databinding.ActivityPlacemarkListBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel
import timber.log.Timber.i

class PlacemarkListView : AppCompatActivity(), PlacemarkListener {

    lateinit var app: MainApp
    lateinit var binding: ActivityPlacemarkListBinding
    lateinit var presenter: PlacemarkListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        presenter = PlacemarkListPresenter(this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        updateRecyclerView()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {

        //update the view
        super.onResume()
        updateRecyclerView()
        binding.recyclerView.adapter?.notifyDataSetChanged()
        i("recyclerView onResume")

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> { presenter.doAddPlacemark() }
            R.id.item_map -> { presenter.doShowPlacemarksMap() }
            R.id.item_logout -> { presenter.doLogout() }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlacemarkClick(placemark: PlacemarkModel) {
        presenter.doEditPlacemark(placemark)

    }

    private fun updateRecyclerView(){
        GlobalScope.launch(Dispatchers.Main){
            binding.recyclerView.adapter =
                PlacemarkAdapter(presenter.getPlacemarks(), this@PlacemarkListView)
        }
    }

}