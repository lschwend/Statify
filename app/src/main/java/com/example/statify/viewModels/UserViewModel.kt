package com.example.statify.viewModels

import androidx.lifecycle.ViewModel
import com.example.statify.data.manager.UserCredentialsRepo
import com.example.statify.data.model.UserCredentials
import io.reactivex.Flowable

class UserViewModel(private val userCredentialsRepo: UserCredentialsRepo) : ViewModel() {

    val userCredentials: Flowable<List<UserCredentials>> =  userCredentialsRepo.getAllUserCredentials()
}