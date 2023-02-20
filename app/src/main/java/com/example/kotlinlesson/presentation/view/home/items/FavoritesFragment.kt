package com.example.kotlinlesson.presentation.view.home.items

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinlesson.App
import com.example.kotlinlesson.databinding.FragmentFavoritesBinding
import com.example.kotlinlesson.presentation.receiver.AirplaneModeChangeReceiver
import com.example.kotlinlesson.presentation.receiver.MyBroadcastReceiver
import com.example.kotlinlesson.presentation.view.home.items.adapter.FavoritesAdapter
import com.example.kotlinlesson.utils.BaseFragment


class FavoritesFragment : BaseFragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favAdapter: FavoritesAdapter

    private lateinit var receiver: AirplaneModeChangeReceiver

    private lateinit var receiver2: MyBroadcastReceiver

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

        receiver = AirplaneModeChangeReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            requireContext().registerReceiver(receiver, it)
        }

        receiver2 = MyBroadcastReceiver()
        IntentFilter("MY_ACTION").also {
            requireContext().registerReceiver(receiver, it)
        }

        setMessage()

        favAdapter = FavoritesAdapter()

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = favAdapter

        viewModel.getFavorites()

        viewModel.favorites.observe(viewLifecycleOwner) {
            favAdapter.submitList(it)
        }


    }
    private fun setMessage(){
        val intent = Intent("MY_ACTION")
        intent.putExtra("KEY", "message")
        requireContext().sendBroadcast(intent)
    }

}