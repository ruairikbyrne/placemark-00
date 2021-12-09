package org.wit.placemark.room

import android.content.Context
import androidx.room.Room
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.models.PlacemarkStore

class PlacemarkStoreRoom(val context: Context) : PlacemarkStore {

    var dao: PlacemarkDao

    init {
        val database = Room.databaseBuilder(context, Database::class.java, "room_sample.db")
            .fallbackToDestructiveMigration()
            .build()
        dao = database.placemarkDao()
    }

    override suspend fun findAll(): List<PlacemarkModel> {

        return dao.findAll()
    }

    override suspend fun findById(id: Long): PlacemarkModel? {
        return dao.findById(id)
    }

    override suspend fun create(placemark: PlacemarkModel) {
        dao.create(placemark)
    }

    override suspend fun update(placemark: PlacemarkModel) {
        dao.update(placemark)
    }

    override suspend fun delete(placemark: PlacemarkModel) {
        dao.deletePlacemark(placemark)
    }
}