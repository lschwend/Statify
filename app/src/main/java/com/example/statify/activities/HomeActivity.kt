package com.example.statify.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statify.App
import com.example.statify.R
import com.example.statify.api.ApiClient
import com.example.statify.api.SessionManager
import com.example.statify.data.adapter.HomeAdapter
import com.example.statify.data.model.Pager
import com.example.statify.data.model.TrackViewModel
import com.example.statify.data.model.TrackViewModelFactory
import com.example.statify.data.model.Tracks
import com.example.statify.data.model.UserViewModel
import com.example.statify.data.model.UserViewModelFactory
import kaaes.spotify.webapi.android.models.Track
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as App).userCredentialsRepo)
    }

    private val trackViewModel: TrackViewModel by viewModels {
        TrackViewModelFactory((application as App).tracksRepo)
    }

    private lateinit var sessionManager: SessionManager

    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        val credentials = userViewModel.credentials.blockingFirst()[0].accessToken
        sessionManager.saveAuthToken(credentials)

        fetchFavoriteTracks()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = HomeAdapter()
        trackViewModel.tracks.observe(this) { list -> adapter.submitList(list) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun fetchFavoriteTracks() {
        apiClient.getApi(this).getTopTracks()
            .enqueue(object : Callback<Pager<Track>> {
                override fun onFailure(call: Call<Pager<Track>>, t: Throwable) {
                    // Error fetching posts
                    val album = t.stackTrace
                }

                override fun onResponse(
                    call: Call<Pager<Track>>,
                    response: Response<Pager<Track>>
                ) {
                    val track = response.body()?.items

                    val tracks = mutableListOf<Tracks>()
                    track?.forEach { tracks.add(Tracks(name = it.name, type = it.type)) }

                    val oldTracks = trackViewModel.deleteAll()

                    tracks.forEach {
                        trackViewModel.insert(it)
                    }
                }
            })
    }

}