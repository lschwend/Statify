package com.example.statify.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Tracks(

    @PrimaryKey(autoGenerate = true)
    var trackId: Long = 0,

    var id: String,

    @ColumnInfo(name = "track_name")
    var name: String,

    var type: String,

    var artists: List<Artists>,
)