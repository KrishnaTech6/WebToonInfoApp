package com.example.webtooninfoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webtooninfoapp.databinding.FragmentHomeBinding
import com.example.webtooninfoapp.utils.animeList

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var animeAdapter: AnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        animeAdapter = AnimeAdapter(requireContext(), animeList)
        binding.rvAnime.adapter = animeAdapter
        binding.rvAnime.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    companion object {

    }
}