package com.dhanshri.jokesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memes_container")
data class Meme(
    val box_count: Int,
    val captions: Int,
    val height: Int,
    @PrimaryKey
    val id: String,
    val name: String,
    val url: String,
    val width: Int
)