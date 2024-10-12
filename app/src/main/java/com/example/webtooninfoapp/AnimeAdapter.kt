package com.example.webtooninfoapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.webtooninfoapp.databinding.AnimeItemBinding
import com.example.webtooninfoapp.model.Anime

class AnimeAdapter(private val context: Context,
                   private var animeList: List<Anime>,
    private val animeViewModel: AnimeViewModel
): RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun getItemCount(): Int = animeList.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, AnimeDetailsActivity::class.java)
            intent.putExtra("anime", animeList[position])
            context.startActivity(intent)
        }
    }
    fun updateData(newAnimeList: List<Anime>) {
        this.animeList = newAnimeList
        notifyDataSetChanged()
    }


    inner class AnimeViewHolder(private val binding : AnimeItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            binding.tvTitle.text = anime.title
            binding.tvDescription.text = anime.description
            binding.tvCreator.text = anime.creator
            binding.tvReads.text = anime.reads
            binding.imageView.setImageResource(anime.imageRes)

            updateLike(anime)

            binding.ivLike.setOnClickListener {
                anime.like = !anime.like //change value of like
                updateLike(anime) // update the like iv

                Log.d("Hello", anime.toString())
                // Save the anime to the Room database when liked or unliked
                if (anime.like) {
                    Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
                }
                //update in both cases (like or unlike)
                animeViewModel.insertOrUpdateAnime(anime)
            }
        }

        private fun updateLike(anime: Anime) {
            if (anime.like) {
                binding.ivLike.setImageResource(R.drawable.like_filled_icon)
            } else {
                binding.ivLike.setImageResource(R.drawable.like_icon)
            }
        }
    }
}