package com.example.statify.util

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.statify.App
import com.example.statify.BuildConfig
import com.example.statify.activities.TrackActivity
import com.example.statify.data.model.UserCredentials
import com.example.statify.data.model.UserViewModel
import com.example.statify.data.model.UserViewModelFactory
import com.pghaz.spotify.webapi.auth.SpotifyAuthorizationCallback
import com.pghaz.spotify.webapi.auth.SpotifyAuthorizationClient
import io.github.kaaes.spotify.webapi.core.models.UserPrivate
import net.openid.appauth.TokenResponse


class SpotifyPkceLoginActivityImpl : AppCompatActivity(),
    SpotifyAuthorizationCallback.Authorize,
    SpotifyAuthorizationCallback.RefreshToken{

    companion object {
        private const val REQUEST_CODE_SPOTIFY_LOGIN = 42
        private val SPOTIFY_CLIENT_ID = BuildConfig.SPOTIFY_CLIENT_ID
        private val REQUEST_CODE = 1337
        private val SPOTIFY_REDIRECT_URI = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE
        private val SCOPES = "app-remote-control,user-read-private,user-library-modify,user-follow-read,user-follow-modify,user-read-recently-played,user-read-currently-playing,user-top-read,user-library-read"
    }

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as App).userCredentialsRepo)
    }

    lateinit var spotifyAuthClient: SpotifyAuthorizationClient

    private fun initSpotifyAuthClient() {
        spotifyAuthClient = SpotifyAuthorizationClient
            .Builder(SPOTIFY_CLIENT_ID, SPOTIFY_REDIRECT_URI)
            .setScopes(
                arrayOf(
                    SCOPES
                )
            )
            .setCustomTabColor(Color.RED)
            .setFetchUserAfterAuthorization(true)
            .build(this)

        spotifyAuthClient.addAuthorizationCallback(this)
        spotifyAuthClient.addRefreshTokenCallback(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSpotifyAuthClient()

        if (spotifyAuthClient.isAuthorized()) {
            if (spotifyAuthClient.getNeedsTokenRefresh()) {
                spotifyAuthClient.refreshAccessToken()
            } else {
                onSpotifyAuthorizedAndAvailable(spotifyAuthClient.getLastTokenResponse()?.accessToken)
            }
        } else {
            spotifyAuthClient.authorize(this, REQUEST_CODE_SPOTIFY_LOGIN)
        }
    }

    private fun onSpotifyAuthorizedAndAvailable(accessToken: String?) {
        // make your Spotify Web API calls here
        val refreshToken = spotifyAuthClient.getLastTokenResponse()?.refreshToken.toString()
        val userCredentials =
            UserCredentials(accessToken = accessToken!!, refreshToken = refreshToken)
        userViewModel.insert(userCredentials)
        val intent = Intent(this, TrackActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // At this point it is authorized but we don't have access token yet.
        // We get it when onAuthorizationSucceed() is called
        spotifyAuthClient.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        spotifyAuthClient.onStart()
    }

    override fun onStop() {
        super.onStop()
        spotifyAuthClient.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        spotifyAuthClient.removeAuthorizationCallback(this)
        spotifyAuthClient.removeRefreshTokenCallback(this)
        spotifyAuthClient.onDestroy()
    }

    override fun onAuthorizationCancelled() {
        Toast.makeText(this, "auth cancelled", Toast.LENGTH_SHORT).show()
    }

    override fun onAuthorizationFailed(error: String?) {
        Toast.makeText(this, "auth failed", Toast.LENGTH_SHORT).show()
    }

    override fun onAuthorizationRefused(error: String?) {
        Toast.makeText(this, "auth refused", Toast.LENGTH_SHORT).show()
    }

    override fun onAuthorizationStarted() {
        Toast.makeText(this, "auth start", Toast.LENGTH_SHORT).show()
    }

    override fun onAuthorizationSucceed(tokenResponse: TokenResponse?, user: UserPrivate?) {
        onSpotifyAuthorizedAndAvailable(tokenResponse?.accessToken)
        val accessToken = spotifyAuthClient.getLastTokenResponse()?.accessToken.toString()
        val refreshToken = spotifyAuthClient.getLastTokenResponse()?.refreshToken.toString()
        val userCredentials =
            UserCredentials(accessToken = accessToken, refreshToken = refreshToken)
        userViewModel.insert(userCredentials)
        val intent = Intent(this, TrackActivity::class.java)
        startActivity(intent)
    }

    override fun onRefreshAccessTokenStarted() {
        Toast.makeText(this, "refresh start", Toast.LENGTH_SHORT).show()
    }

    override fun onRefreshAccessTokenSucceed(tokenResponse: TokenResponse?, user: UserPrivate?) {
        onSpotifyAuthorizedAndAvailable(tokenResponse?.accessToken)
    }
}