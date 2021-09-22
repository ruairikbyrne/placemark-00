package org.wit.placemark.main

import android.app.Application
import org.wit.placemark.models.PlacemarkMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    //val placemarks = ArrayList<PlacemarkModel>()
    val placemarks = PlacemarkMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Placemark started")
        //placemarks.add(PlacemarkModel("One", "About one..."))
        //placemarks.add(PlacemarkModel("Two", "About two..."))
        //placemarks.add(PlacemarkModel("Three", "About three..."))
    }

}