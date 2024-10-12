package com.example.webtooninfoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webtooninfoapp.databinding.FragmentHomeBinding
import com.example.webtooninfoapp.utils.animeList

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var animeViewModel: AnimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Initialize ViewModel
        animeViewModel = ViewModelProvider(this).get(AnimeViewModel::class.java)

        // Initialize the adapter with the list from the ViewModel
        animeAdapter = AnimeAdapter(requireContext(), emptyList(), animeViewModel)
        binding.rvAnime.adapter = animeAdapter
        binding.rvAnime.layoutManager = LinearLayoutManager(requireContext())


        // Observe changes in the list of all animes
        animeViewModel.getAllAnimes().observe(context as LifecycleOwner) { animes ->
            animeAdapter.updateData(animes)
            if (animes.isEmpty()) {
                for (anime in animeList) {
                    animeViewModel.insertOrUpdateAnime(anime)
                }
            }
        }

        return binding.root
    }
}
