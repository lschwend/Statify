package com.example.statify.data.helper

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.statify.data.dao.UserCredentialsDao
import com.example.statify.data.model.UserCredentials

@Database(
    entities = [
        UserCredentials::class
    ],
    version = 1
)
abstract class DataBaseHelper : RoomDatabase(){

    abstract fun userCredentialsDao(): UserCredentialsDao

    companion object {

        @Volatile
        private var INSTANCE: DataBaseHelper? = null

        fun getInstance(context: Context): DataBaseHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            androidx.room.Room.databaseBuilder(
                context.applicationContext,
                DataBaseHelper::class.java,
                "statfiy.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}