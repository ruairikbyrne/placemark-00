package org.wit.placemark.room

import androidx.room.*
import org.wit.placemark.models.PlacemarkModel

@Dao
interface PlacemarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(placemark: PlacemarkModel)

    @Query("SELECT * FROM PlacemarkModel")
    suspend fun findAll(): List<PlacemarkModel>

    @Query("select * from PlacemarkModel where id = :id")
    suspend fun findById(id: Long): PlacemarkModel

    @Update
    suspend fun update(placemark: PlacemarkModel)

    @Delete
    suspend fun deletePlacemark(placemark: PlacemarkModel)
}