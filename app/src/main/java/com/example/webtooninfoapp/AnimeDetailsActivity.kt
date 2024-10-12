package com.example.webtooninfoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.webtooninfoapp.databinding.ActivityAnimeDetailsBinding
import com.example.webtooninfoapp.model.Anime

class AnimeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeDetailsBinding
    private val TAG = "AnimeDetailsActivity"
    private lateinit var animeViewModel: AnimeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        animeViewModel = ViewModelProvider(this).get(AnimeViewModel::class.java)

        val anime = intent.getParcelableExtra<Anime>("anime")
        if (anime != null) {
            binding.ivAnimeImage.setImageResource(anime.imageRes)
            binding.tvAnimeTitle.text = anime.title
            binding.tvAnimeCreator.text = anime.creator
            binding.tvAnimeDescription.text = anime.description
            binding.tvAnimeReads.text = anime.reads

            // Check if anime is liked
            updateLikeIcon(anime.like)
            binding.ratingBar.rating = anime.rating
            binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                anime.rating = rating
                animeViewModel.insertOrUpdateAnime(anime)
            }

            binding.ivAnimeLike.setOnClickListener {
                anime.like = !anime.like
                updateLikeIcon(anime.like)
                if (anime.like) {
                    animeViewModel.insertOrUpdateAnime(anime)
                    Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show()
                } else {
                    animeViewModel.insertOrUpdateAnime(anime)  // Update in DB even when unliked
                }
            }
        }

    }

    private fun updateLikeIcon(isLiked: Boolean) {
        if (isLiked) {
            binding.ivAnimeLike.setImageResource(R.drawable.like_filled_icon)
        } else {
            binding.ivAnimeLike.setImageResource(R.drawable.like_icon)
        }
    }
}