package com.example.statify.api

import android.content.Context
import java.io.Serializable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private lateinit var api: ApiService

    val SPOTIFY_WEB_API_ENDPOINT = "https://api.spotify.com/v1/"


    fun getApi(context: Context): ApiService {

        // Initialize ApiService if not initialized yet
        if (!::api.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(SPOTIFY_WEB_API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            api = retrofit.create(ApiService::class.java)
        }

        return api
    }

    /**
     * Initialize OkhttpClient with our interceptor
     */
    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

}

