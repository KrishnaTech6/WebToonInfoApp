package com.example.webtooninfoapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.webtooninfoapp.model.Anime

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: Anime)

    @Query("SELECT * FROM animeList WHERE like = 1")
    fun getFavoriteAnimes(): LiveData<List<Anime>>

    @Query("SELECT * FROM animeList")
    fun getAllWords(): LiveData<List<Anime>>

    @Delete
    suspend fun deleteAnime(anime: Anime)

}