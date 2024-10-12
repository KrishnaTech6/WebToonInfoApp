package com.example.webtooninfoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.webtooninfoapp.model.Anime
import com.example.webtooninfoapp.room.AnimeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeViewModel(application: Application): AndroidViewModel(application) {
    private val animeDao = AnimeDatabase.getDatabase(application).animeDao()

    fun insertOrUpdateAnime(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingAnime = animeDao.getAnimeById(anime.id)
            if (existingAnime != null) {
                animeDao.updateAnime(anime)
            } else {
                animeDao.insertAnime(anime)
            }
        }
    }


    fun getFavoriteAnimes(): LiveData<List<Anime>> {
        return animeDao.getFavoriteAnimes()
    }
    // Get all anime from the database
    fun getAllAnimes(): LiveData<List<Anime>> {
        return animeDao.getAllAnimes()  // Assuming getAllWords() fetches all anime including non-favorites
    }
}
