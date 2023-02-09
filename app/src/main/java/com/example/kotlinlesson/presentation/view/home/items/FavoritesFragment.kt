package com.example.kotlinlesson.presentation.view.home.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinlesson.App
import com.example.kotlinlesson.databinding.FragmentFavoritesBinding
import com.example.kotlinlesson.presentation.view.home.items.adapter.FavoritesAdapter
import com.example.kotlinlesson.utils.BaseFragment


class FavoritesFragment : BaseFragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favAdapter: FavoritesAdapter


    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

        favAdapter = FavoritesAdapter()

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = favAdapter

        viewModel.getFavorites()

        viewModel.favorites.observe(viewLifecycleOwner) {
            favAdapter.submitList(it)
        }


    }
}