package com.example.kotlinlesson.presentation.view.home.items

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kotlinlesson.App
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentDetailsBinding
import com.example.kotlinlesson.utils.BaseFragment
import com.example.kotlinlesson.utils.BundleConstants.IMAGE_VIEW
import com.squareup.picasso.Picasso


class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsVIewModel by viewModels { viewModelFactory }

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

        (requireActivity().applicationContext as App).provideAppComponent().inject(this)
        val bundle = arguments
        bundle?.let { safeBundle ->
            val description = safeBundle.getString("description")
            val image = safeBundle.getString(IMAGE_VIEW)

            binding.detailsName.text = description
            Picasso.get().load(Uri.parse(image)).into(binding.detailsImage)
        }

        binding.btnLogout.setOnClickListener {
//            viewModel.logoutUser()
            binding.btnLogout.isPressed = !it.isPressed
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