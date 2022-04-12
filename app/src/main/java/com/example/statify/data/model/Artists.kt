package com.example.statify.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artists (

    @PrimaryKey(autoGenerate = true)
    var artistId: Long = 0,

    @ColumnInfo(name = "artist_name")
    var name: String,

    var uri: String,

    var images: List<Images>

)