package com.example.statify

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.statify.data.model.Model

class App : Application() {
    lateinit var model: Model

    override fun onCreate() {
        super.onCreate()
        model = Model
        context = applicationContext
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    companion object {
        lateinit var context: Context
    }
}