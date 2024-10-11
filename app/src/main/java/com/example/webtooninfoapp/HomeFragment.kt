package com.example.webtooninfoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webtooninfoapp.databinding.FragmentHomeBinding

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

        // Initialize the adapter with the list from the ViewModel (or dummy list for now)
        animeAdapter = AnimeAdapter(requireContext(), mutableListOf(), animeViewModel)
        binding.rvAnime.adapter = animeAdapter
        binding.rvAnime.layoutManager = LinearLayoutManager(requireContext())

        // Observe changes in the list of all animes
        animeViewModel.getAllAnimes().observe(viewLifecycleOwner) { animes ->
            animeAdapter.updateData(animes)
        }

        return binding.root
    }
}
