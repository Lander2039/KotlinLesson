package com.example.kotlinlesson.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentDetailsBinding
import com.example.kotlinlesson.presentation.view.auth.LoginFragment
import com.example.kotlinlesson.presentation.view.home.ItemsFragment.Companion.DATE
import com.example.kotlinlesson.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinlesson.utils.BundleConstants.NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment(), DetailsView {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsPresenter.setView(this)

        val bundle = arguments
        bundle?.let { safeBundle ->
            detailsPresenter.getArguments(
                safeBundle.getString(NAME),
                safeBundle.getString(DATE),
                safeBundle.getInt(IMAGE_VIEW)
            )
        }

        binding.btnLogout.setOnClickListener {
            detailsPresenter.logoutUser()
        }
    }

    override fun userLoggedOut() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, LoginFragment())
            .commit()
    }

    override fun displayItemDate(name: String, date: String, imageView: Int) {
        binding.detailsName.text = name
        binding.detailsDate.text = date
        binding.detailsImage.setBackgroundResource(imageView)
    }
}