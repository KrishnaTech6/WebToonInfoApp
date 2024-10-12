package com.example.webtooninfoapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.webtooninfoapp.model.Anime

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: Anime)

    @Update
    suspend fun updateAnime(anime: Anime)

    @Query("SELECT * FROM animeList WHERE id = :id LIMIT 1")
    suspend fun getAnimeById(id: Int): Anime?

    @Query("SELECT * FROM animeList WHERE `like` = 1")
    fun getFavoriteAnimes(): LiveData<List<Anime>>

    @Query("SELECT * FROM animeList")
    fun getAllAnimes(): LiveData<List<Anime>>

    @Delete
    suspend fun deleteAnime(anime: Anime)

}