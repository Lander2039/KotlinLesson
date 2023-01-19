package com.example.kotlinlesson.presentation.view.home

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentDetailsBinding
import com.example.kotlinlesson.utils.BundleConstants.IMAGE_VIEW
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsVIewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let { safeBundle ->
            val description = safeBundle.getString("description")
            val image = safeBundle.getString(IMAGE_VIEW)

            binding.detailsName.text = description
            Picasso.get().load(Uri.parse(image)).into(binding.detailsImage)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser()
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null) {
                val navGraph = findNavController().navInflater.inflate(
                    it
                )
                navGraph.startDestination = R.id.loginFragment
                findNavController().graph = navGraph
            }
        }

    }
}