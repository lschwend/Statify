package com.example.statify.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.statify.data.model.Artists

@Dao
interface ArtistDao {

    @Query("SELECT * FROM Artists")
    fun getAll(): LiveData<List<Artists>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(artists: List<Artists>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(artist: Artists)

    @Query("DELETE FROM Artists")
    suspend fun clearTable()
}