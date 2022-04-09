package com.example.statify.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.statify.data.model.Tracks
import io.reactivex.Flowable

@Dao
interface TrackDao {

    @Query("SELECT * FROM Tracks")
    fun getAll(): LiveData<List<Tracks>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(tracks: List<Tracks>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tracks: Tracks)

    @Query("DELETE FROM Tracks")
    suspend fun clearTable()
}