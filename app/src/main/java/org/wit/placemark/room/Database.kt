package org.wit.placemark.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.wit.placemark.helpers.Converters
import org.wit.placemark.models.PlacemarkModel

@Database(entities = arrayOf(PlacemarkModel::class), version = 1,  exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun placemarkDao(): PlacemarkDao
}