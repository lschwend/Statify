package com.example.statify.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.statify.data.manager.UserCredentialsRepo
import io.reactivex.Flowable
import kotlinx.coroutines.launch

class UserViewModel (private val repository: UserCredentialsRepo) : ViewModel() {

    val credentials: Flowable<List<UserCredentials>> = repository.getAllUserCredentials()

    fun insert(credentials: UserCredentials) = viewModelScope.launch {
        repository.saveUserCredentials(credentials)
    }
}

class UserViewModelFactory(private val repository: UserCredentialsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}