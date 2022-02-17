package com.example.statify.services

import android.util.Log
import com.example.statify.data.model.UserCredentials
import kaaes.spotify.webapi.android.SpotifyApi
import kaaes.spotify.webapi.android.models.Pager
import kaaes.spotify.webapi.android.models.Track
import retrofit.Callback

class SongService (private val userCredentials: UserCredentials) {

    val api = SpotifyApi()

    fun getAlbum(api: SpotifyApi) {
        val service = api.service

        val topTracks = service.topTracks

        Log.v(topTracks.toString(), topTracks.toString())
    }





}