package com.example.statify.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.statify.data.model.UserCredentials
import io.reactivex.Flowable

@Dao
interface UserCredentialsDao {

    @Query("SELECT * FROM UserCredentials")
    fun getAll(): Flowable<List<UserCredentials>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg data: UserCredentials): List<Long>

    @Query("DELETE FROM UserCredentials")
    fun clearTable()
}
