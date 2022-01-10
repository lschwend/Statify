package com.example.statify.activities

import android.os.Bundle
import android.view.View
import com.example.statify.R

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        guardValidSpotifyApi(HomeActivity::class.java) { api ->
//            if (!api.isTokenValid(true).isValid) throw SpotifyException.ReAuthenticationNeededException()
//            println(api.personalization.getTopTracks(limit = 5).items.map { it.name })
//        }
        setContentView(R.layout.activity_home)


    }

    fun requestInfo(view: View) {

    }
}