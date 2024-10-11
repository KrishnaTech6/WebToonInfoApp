package com.example.webtooninfoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.webtooninfoapp.databinding.ActivityAnimeDetailsBinding

class AnimeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeDetailsBinding
    private val TAG = "AnimeDetailsActivity"
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
        val anime = intent.getParcelableExtra<Anime>("anime")
        if (anime != null) {
            binding.ivAnimeImage.setImageResource(anime.imageRes)
            binding.tvAnimeTitle.text = anime.title
            binding.tvAnimeCreator.text = anime.creator
            binding.tvAnimeDescription.text = anime.description
            binding.tvAnimeReads.text = anime.reads
            if(anime.like){
                binding.ivAnimeLike.setImageResource(R.drawable.like_filled_icon)
            }else{
                binding.ivAnimeLike.setImageResource(R.drawable.like_icon)
            }

            binding.ivAnimeLike.setOnClickListener {
                anime.like = !anime.like
                if(anime.like){
                    binding.ivAnimeLike.setImageResource(R.drawable.like_filled_icon)
                    Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show()
                }else{
                    binding.ivAnimeLike.setImageResource(R.drawable.like_icon)
                }
            }
        }

    }
}