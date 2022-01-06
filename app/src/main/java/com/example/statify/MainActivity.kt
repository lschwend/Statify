package com.example.statify

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.adamratzman.spotify.getSpotifyPkceAuthorizationUrl
import com.adamratzman.spotify.getSpotifyPkceCodeChallenge

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loginApi(view: View) {
        this.startSpotifyClientPkceLoginActivity(SpotifyPkceLoginActivityImpl::class.java)
    }

    private fun generateUrl() : String {
        val codeVerifier = "thisisaveryrandomalphanumericcodeverifierandisgreaterthan43characters"
        val codeChallenge = getSpotifyPkceCodeChallenge(codeVerifier) // helper method
        return getSpotifyPkceAuthorizationUrl(
            SpotifyScope.PLAYLIST_READ_PRIVATE,
            SpotifyScope.PLAYLIST_MODIFY_PRIVATE,
            SpotifyScope.USER_FOLLOW_READ,
            SpotifyScope.USER_LIBRARY_MODIFY,
            clientId = BuildConfig.SPOTIFY_CLIENT_ID,
            redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE,
            codeChallenge = codeChallenge
        )
    }

}