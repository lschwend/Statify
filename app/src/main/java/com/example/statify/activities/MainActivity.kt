package com.example.statify.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.statify.R
import com.example.statify.util.SpotifyPkceLoginActivityImpl

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun loginApi(view: View) {
        val intent = Intent(this, SpotifyPkceLoginActivityImpl::class.java)
        startActivity(intent)
    }




}