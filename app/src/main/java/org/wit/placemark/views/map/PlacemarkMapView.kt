package org.wit.placemark.views.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.wit.placemark.databinding.ActivityPlacemarkMapsBinding
import org.wit.placemark.databinding.ContentPlacemarkMapsBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel

class PlacemarkMapView : AppCompatActivity() , GoogleMap.OnMarkerClickListener{

    private lateinit var binding: ActivityPlacemarkMapsBinding
    private lateinit var contentBinding: ContentPlacemarkMapsBinding
    lateinit var app: MainApp
    lateinit var presenter: PlacemarkMapPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        binding = ActivityPlacemarkMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAdd)

        presenter = PlacemarkMapPresenter(this)

        contentBinding = ContentPlacemarkMapsBinding.bind(binding.root)

        contentBinding.mapView.onCreate(savedInstanceState)
        contentBinding.mapView.getMapAsync{
            GlobalScope.launch(Dispatchers.Main) {
                presenter.doPopulateMap(it)
            }
        }
    }
    override fun onMarkerClick(marker: Marker): Boolean {
        GlobalScope.launch(Dispatchers.Main) {
            presenter.doMarkerSelected(marker)
        }
        return true
    }
    fun showPlacemark(placemark: PlacemarkModel) {
        contentBinding.currentTitle.text = placemark.title
        contentBinding.currentDescription.text = placemark.description
        Picasso.get()
            .load(placemark.image)
            .into(contentBinding.imageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        contentBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        contentBinding.mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        contentBinding.mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        contentBinding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        contentBinding.mapView.onSaveInstanceState(outState)
    }


}