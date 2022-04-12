package com.example.statify.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.statify.data.manager.ArtistsRepo
import com.example.statify.data.manager.TracksRepo
import kotlinx.coroutines.launch

class ArtistViewModel (private val repo: ArtistsRepo) : ViewModel(){

    val artists : LiveData<List<Artists>> = repo.getAllArtists()

    fun insert(artist: Artists) = viewModelScope.launch {
        repo.insertArtist(artist)
    }

    fun deleteAll() = viewModelScope.launch {
        repo.deleteAll()
    }
}

class ArtistViewModelFactory(private val repository: ArtistsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtistViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}