package com.example.statify.data.manager

import com.example.statify.data.dao.UserCredentialsDao
import com.example.statify.data.helper.DataBaseHelper
import com.example.statify.data.model.UserCredentials
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class UserCredentialsRepo @Inject constructor(
    private val userCredentialsDao: UserCredentialsDao
){

    fun saveUserAllCredentials(userCredentials: List<UserCredentials>) {
        userCredentialsDao.insertAll(userCredentials)
    }

    fun saveUserCredentials(userCredentials: UserCredentials) {
        userCredentialsDao.insert(userCredentials)
    }

    fun getAllUserCredentials(): Flowable<List<UserCredentials>> = userCredentialsDao.getAll()

}