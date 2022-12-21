package com.example.kotlinlesson.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.kotlinlesson.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinlesson.utils.BundleConstants.NAME
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentDetailsBinding
import com.example.kotlinlesson.presentation.view.auth.LoginFragment
import com.example.kotlinlesson.presentation.view.home.ItemsFragment.Companion.DATE
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
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let { safeBundle ->
            val name = safeBundle.getString(NAME)
            val date = safeBundle.getString(DATE)
            val image = safeBundle.getInt(IMAGE_VIEW)

            binding.detailsName.text = name
            binding.detailsDate.text = date
            binding.detailsImage.setBackgroundResource(image)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser()
        }

        viewModel.nav.observe(viewLifecycleOwner){
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container,LoginFragment())
                .commit()
        }

    }
}