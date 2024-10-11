package com.example.webtooninfoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webtooninfoapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var animeViewModel: AnimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val animeAdapter = AnimeAdapter(requireContext(), mutableListOf(), animeViewModel)
        binding.rvAnimeFavorites.adapter = animeAdapter
        binding.rvAnimeFavorites.layoutManager = LinearLayoutManager(requireContext())

        animeViewModel = ViewModelProvider(this).get(AnimeViewModel::class.java)

        // Observe favorite anime list
        animeViewModel.getFavoriteAnimes().observe(viewLifecycleOwner) { favoriteAnimes ->
            animeAdapter.updateData(favoriteAnimes)  // Assuming updateData() is implemented in AnimeAdapter
        }
        return binding.root
    }

    companion object {

    }
}