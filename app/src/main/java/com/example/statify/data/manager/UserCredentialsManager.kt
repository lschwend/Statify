package com.example.statify.data.manager

import com.example.statify.data.helper.DataBaseHelper
import com.example.statify.data.model.UserCredentials
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class UserCredentialsManager @Inject constructor(
    private val dataBaseHelper: DataBaseHelper
){

    fun saveUserCredentials(userCredentials: List<UserCredentials>) : Completable {
        return Completable.fromAction {
            dataBaseHelper.userCredentialsDao().insertAll(*userCredentials.toTypedArray())
        }
    }
}