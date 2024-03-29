package com.example.statify.data.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.statify.data.dao.ArtistDao
import com.example.statify.data.dao.TrackDao
import com.example.statify.data.dao.UserCredentialsDao
import com.example.statify.data.model.Artists
import com.example.statify.data.model.Tracks
import com.example.statify.data.model.UserCredentials
import com.example.statify.util.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserCredentials::class,
        Tracks::class,
        Artists::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DataBaseHelper : RoomDatabase(){

    abstract fun userCredentialsDao(): UserCredentialsDao

    abstract fun tracksDao(): TrackDao

    abstract fun artistsDao(): ArtistDao

    companion object {

        @Volatile
        private var INSTANCE: DataBaseHelper? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DataBaseHelper {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseHelper::class.java,
                    "user_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.userCredentialsDao(), database.tracksDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(userCredentialsDao: UserCredentialsDao, trackDao: TrackDao) {

        }
    }
}