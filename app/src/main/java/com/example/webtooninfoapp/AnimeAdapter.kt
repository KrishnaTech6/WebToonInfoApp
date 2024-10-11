package com.example.webtooninfoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webtooninfoapp.databinding.AnimeItemBinding

class AnimeAdapter(private val context: Context,private val animeList: List<Anime>): RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {
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



    inner class AnimeViewHolder(private val binding : AnimeItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            binding.tvTitle.text = anime.title
            binding.tvDescription.text = anime.description
            binding.tvCreator.text = anime.creator
            binding.tvReads.text = anime.reads
            binding.imageView.setImageResource(anime.imageRes)

            binding.ivLike.setOnClickListener {
                if(anime.like){
                    binding.ivLike.setImageResource(R.drawable.like_icon)
                    anime.like= false
                }else{
                    binding.ivLike.setImageResource(R.drawable.like_filled_icon)
                    anime.like = true
                }
            }
        }
    }
}