package com.example.statify.data.manager

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.statify.data.dao.TrackDao
import com.example.statify.data.model.Tracks
import io.reactivex.Flowable

class TracksRepo(private val tracksDao: TrackDao) {

    suspend fun saveTracks(tracks: List<Tracks>) {
        tracksDao.insertAll(tracks)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTracks(tracks: Tracks) {
        tracksDao.insert(tracks)
    }

    fun getAllTracks(): LiveData<List<Tracks>> = tracksDao.getAll()

    suspend fun deleteAll() {
        tracksDao.clearTable()
    }
}