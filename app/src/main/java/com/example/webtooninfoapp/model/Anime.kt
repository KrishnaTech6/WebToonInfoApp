package com.example.webtooninfoapp.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "animeList")
data class Anime(
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val title: String,
    val description: String,
    val creator: String,
    val reads: String,
    @DrawableRes
    val imageRes: Int,
    var like: Boolean,
    var rating: Float= 0f
): Parcelable
