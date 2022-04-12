package com.example.statify.data.manager

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.statify.data.dao.ArtistDao
import com.example.statify.data.model.Artists

class ArtistsRepo (private val artistDao: ArtistDao){

    suspend fun saveArtists(artists: List<Artists>) {
        artistDao.insertAll(artists)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertArtist(artists: Artists) {
        artistDao.insert(artists)
    }

    fun getAllArtists(): LiveData<List<Artists>> = artistDao.getAll()

    suspend fun deleteAll() {
        artistDao.clearTable()
    }
}