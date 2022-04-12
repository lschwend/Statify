package com.example.statify.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statify.App
import com.example.statify.R
import com.example.statify.api.ApiClient
import com.example.statify.data.adapter.ArtistAdapter
import com.example.statify.data.model.ArtistViewModel
import com.example.statify.data.model.ArtistViewModelFactory
import com.example.statify.data.model.Artists
import com.example.statify.data.model.Pager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsActivity() : BaseActivity() {
    private val artistViewModel: ArtistViewModel by viewModels {
        ArtistViewModelFactory((application as App).artistsRepo)
    }

    private lateinit var apiClient: ApiClient

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)

        apiClient = ApiClient()

        fetchFavoriteAlbums()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ArtistAdapter()
        artistViewModel.artists.observe(this) { list -> adapter.submitList(list) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun getTracks(view: View) {
        val intent = Intent(this, TrackActivity()::class.java)
        startActivity(intent)
    }

    private fun fetchFavoriteAlbums() {
        apiClient.getApi(this).getTopArtists()
            .enqueue(object : Callback<Pager<Artists>> {
                override fun onFailure(call: Call<Pager<Artists>>, t: Throwable) {
                    // Error fetching posts
                    t.stackTrace
                }

                override fun onResponse(
                    call: Call<Pager<Artists>>,
                    response: Response<Pager<Artists>>
                ) {
                    val artists = response.body()?.items

                    val newArtists = mutableListOf<Artists>()
                    artists?.forEach {
                        newArtists.add(
                            Artists(
                                name = it.name,
                                uri = it.uri,
                                images = it.images
                            )
                        )
                    }

                    artistViewModel.deleteAll()

                    newArtists.forEach {
                        artistViewModel.insert(it)
                    }
                }
            })
    }

}