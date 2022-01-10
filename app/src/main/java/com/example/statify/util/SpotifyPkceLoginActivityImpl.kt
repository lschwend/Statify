package com.example.statify.util

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.statify.BuildConfig
import com.example.statify.activities.HomeActivity
import com.example.statify.data.manager.UserCredentialsManager
import com.example.statify.data.model.UserCredentials
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import javax.inject.Inject

internal var pkceClassBackTo: Class<out Activity>? = null

class SpotifyPkceLoginActivityImpl() : AppCompatActivity() {
    private val clientId = BuildConfig.SPOTIFY_CLIENT_ID
    private val REQUEST_CODE = 1337
    private val REDIRECT_URI = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE

    @Inject
    lateinit var userCredentialsManager: UserCredentialsManager

    override fun onStart() {
        super.onStart()
        val builder =
            AuthorizationRequest.Builder(clientId, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)

        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        val homeIntent = Intent(this, HomeActivity::class.java)
        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> saveToken(homeIntent, response)
                AuthorizationResponse.Type.ERROR -> {}
                else -> {}
            }
        }
    }

    private fun saveToken(intent: Intent?, response: AuthorizationResponse) {
        val userCredentials = UserCredentials(1, response.accessToken)
        val list = listOf(userCredentials)
        userCredentialsManager.saveUserCredentials(list)
        startActivity(intent)
    }

}