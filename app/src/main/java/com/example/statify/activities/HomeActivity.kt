package com.example.statify.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.statify.App
import com.example.statify.R
import com.example.statify.data.model.UserViewModel
import com.example.statify.data.model.UserViewModelFactory

class HomeActivity : BaseActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as App).userCredentialsRepo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewHome)
        // val adapter = W

    }

}