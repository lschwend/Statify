package com.example.statify.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Images(

    @PrimaryKey(autoGenerate = true)
    var imageId: Long = 0,

    var url: String,

    var height: Int,

    var width: Int

)