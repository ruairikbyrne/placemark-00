package org.wit.placemark.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlacemarkMemStore : PlacemarkStore {

    val placemarks = ArrayList<PlacemarkModel>()

    override suspend fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

    override suspend fun create(placemark: PlacemarkModel) {
        placemark.id = getId()
        placemarks.add(placemark)
        logAll()
    }

    suspend override fun update(placemark: PlacemarkModel) {
        var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == placemark.id }
        if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
            foundPlacemark.image = placemark.image
            foundPlacemark.location = placemark.location
            logAll();
        }
    }

    override suspend fun delete(placemark: PlacemarkModel) {
        placemarks.remove(placemark)
        logAll()
    }

    private fun logAll() {
        placemarks.forEach { i("$it") }
    }
    override suspend fun findById(id:Long) : PlacemarkModel? {
        val foundPlacemark: PlacemarkModel? = placemarks.find { it.id == id }
        return foundPlacemark
    }
}