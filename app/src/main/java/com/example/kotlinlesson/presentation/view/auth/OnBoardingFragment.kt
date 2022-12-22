package com.example.kotlinlesson.presentation.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentOnBoardingBinding
import com.example.kotlinlesson.presentation.view.home.ItemsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : Fragment(),OnBoardingView {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding: FragmentOnBoardingBinding get() = _binding!!

    @Inject
    lateinit var onBoardingPresenter: OnBoardingPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBoardingPresenter.setView(this)

        binding.btnFinish.setOnClickListener {
            onBoardingPresenter.goToItemsFragment()
        }
    }

    override fun goToItemsFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, ItemsFragment())
            .commit()
    }
}