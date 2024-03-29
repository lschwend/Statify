package com.example.statify.api

import com.example.statify.data.model.Album
import com.example.statify.data.model.Artists
import com.example.statify.data.model.Pager
import com.example.statify.data.model.Tracks
import kaaes.spotify.webapi.android.models.Track
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("albums/{id}")
    fun getAlbum(@Path("id") albumId: String?) : Call<Album>

    @GET("me/top/tracks")
    fun getTopTracks() : Call<Pager<Tracks>>

    @GET("me/top/artists")
    fun getTopArtists() : Call<Pager<Artists>>
}