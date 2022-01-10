package com.example.statify.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

@Entity
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
data class UserCredentials(

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0,

    var accessToken: String = "",


)
