package com.example.statify.data.model

import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.example.statify.App
import com.example.statify.BuildConfig


object Model {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            clientId = BuildConfig.SPOTIFY_CLIENT_ID,
            redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE,
            applicationContext = App.context
        )
    }
}