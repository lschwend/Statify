package com.example.statify.activities

import android.os.Bundle
import com.adamratzman.spotify.SpotifyException
import com.example.statify.R
import com.example.statify.util.guardValidSpotifyApi

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        guardValidSpotifyApi(HomeActivity::class.java) { api ->
            if (!api.isTokenValid(true).isValid) throw SpotifyException.ReAuthenticationNeededException()
            println(api.personalization.getTopTracks(limit = 5).items.map { it.name })
        }
        setContentView(R.layout.activity_home)

    }
}