package com.example.statify

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.statify.data.helper.DataBaseHelper
import com.example.statify.data.manager.ArtistsRepo
import com.example.statify.data.manager.TracksRepo
import com.example.statify.data.manager.UserCredentialsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { DataBaseHelper.getDatabase(this, applicationScope)}
    val userCredentialsRepo by lazy { UserCredentialsRepo(database.userCredentialsDao())}
    val tracksRepo by lazy { TracksRepo(database.tracksDao())}
    val artistsRepo by lazy { ArtistsRepo(database.artistsDao()) }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

    }

}