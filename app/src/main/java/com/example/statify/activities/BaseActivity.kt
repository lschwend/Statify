package com.example.statify.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.statify.data.model.Model

abstract class BaseActivity : AppCompatActivity() {
    lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
