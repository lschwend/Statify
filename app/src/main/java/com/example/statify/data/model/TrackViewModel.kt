package com.example.statify.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.statify.data.manager.TracksRepo
import io.reactivex.Flowable
import kotlinx.coroutines.launch

class TrackViewModel (private val repo: TracksRepo): ViewModel() {

    val tracks : LiveData<List<Tracks>> = repo.getAllTracks()

    fun insert(tracks: Tracks) = viewModelScope.launch {
        repo.insertTracks(tracks)
    }

    fun deleteAll() = viewModelScope.launch {
        repo.deleteAll()
    }
}

class TrackViewModelFactory(private val repository: TracksRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrackViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}