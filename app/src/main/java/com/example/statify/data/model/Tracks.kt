package com.example.statify.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tracks(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var name: String,

    var type: String
)