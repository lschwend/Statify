package com.example.statify.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.statify.data.model.UserCredentials
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCredentialsDao {

    @Query("SELECT * FROM UserCredentials")
    fun getAll(): Flow<List<UserCredentials>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userCredentials: List<UserCredentials>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userCredentials: UserCredentials)

    @Query("DELETE FROM UserCredentials")
    suspend fun clearTable()
}
