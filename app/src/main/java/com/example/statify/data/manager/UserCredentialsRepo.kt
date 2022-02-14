package com.example.statify.data.manager

import androidx.annotation.WorkerThread
import com.example.statify.data.dao.UserCredentialsDao
import com.example.statify.data.model.UserCredentials
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

    fun getAllUserCredentials(): Flow<List<UserCredentials>> = userCredentialsDao.getAll()

}