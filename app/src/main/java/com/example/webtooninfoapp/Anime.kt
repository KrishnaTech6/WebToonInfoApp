package com.example.webtooninfoapp

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val title: String,
    val description: String,
    val creator: String,
    val reads: String,
    @DrawableRes
    val imageRes: Int,
    var like: Boolean
): Parcelable
