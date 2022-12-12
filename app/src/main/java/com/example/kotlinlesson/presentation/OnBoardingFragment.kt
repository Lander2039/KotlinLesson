package com.example.kotlinlesson.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentOnBoardingBinding
import com.example.kotlinlesson.presentation.model.OnBoardingViewModel


class OnBoardingFragment : Fragment() {

    private val viewModel: OnBoardingViewModel by viewModels()

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding: FragmentOnBoardingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel  = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.nav.observe(viewLifecycleOwner){
            if(it!=null) {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_container, ItemsFragment())
                    .commit()
                viewModel.finishPerformed()
            }
        }
    }
}