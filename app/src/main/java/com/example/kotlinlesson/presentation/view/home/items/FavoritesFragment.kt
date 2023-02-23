package com.example.kotlinlesson.presentation.view.home.items

import android.content.Context.LOCATION_SERVICE
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
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
//
//        var locationManager: LocationManager? = null
//
//        locationManager =requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager?
//
//        try {
//            locationManager?.requestLocationUpdates(
//                NETWORK_PROVIDER,
//                0.0f,
//                0.0f,
//                locationListener
//            )
//        }catch (e: java.lang.Exception){
//            Log.w("error", "while accessing location")
//        }
//    }
//    val locationListener = LocationListener{
//        Toast.makeText(requireContext(),"long: ${it.longitude} lat: ${it.latitude}", Toast.LENGTH_SHORT)
    }


    }
