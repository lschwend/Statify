package com.example.statify.data.manager

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.statify.data.dao.UserCredentialsDao
import com.example.statify.data.model.UserCredentials
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

open class UserCredentialsRepo (private val userCredentialsDao: UserCredentialsDao){

    suspend fun saveUserAllCredentials(userCredentials: List<UserCredentials>) {
        userCredentialsDao.insertAll(userCredentials)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun saveUserCredentials(userCredentials: UserCredentials) {
        userCredentialsDao.insert(userCredentials)
    }

    fun getAllUserCredentials(): Flowable<List<UserCredentials>> = userCredentialsDao.getAll()

}