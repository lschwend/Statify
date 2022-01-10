package com.example.statify.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adamratzman.spotifyandroidexample.data.Model
import com.example.statify.App

abstract class BaseActivity : AppCompatActivity() {
    lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (application as App).model
    }
}
