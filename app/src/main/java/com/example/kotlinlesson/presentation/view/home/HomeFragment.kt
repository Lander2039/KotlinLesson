package com.example.kotlinlesson.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinlesson.App
import com.example.kotlinlesson.databinding.FragmentHomeBinding
import com.example.kotlinlesson.utils.BaseFragment
import com.example.kotlinlesson.utils.Coroutines.Coroutines
import com.example.kotlinlesson.utils.NavHelper.replaceGraph


class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

//
//        lifecycleScope.launch(Dispatchers.IO) {
//            viewModel.showUserDate()
//
//
//            withContext(Dispatchers.Main){
//            binding.bntGoToBoarding.setOnClickListener {
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.activity_container, OnBoardingFragment())
//                    .commit()
//            }
//            }
//        }

        Coroutines().testCoroutineCansel()

        viewModel.showUserDate()

        binding.bntGoToBoarding.setOnClickListener {
            viewModel.userLogout()
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null) {
                replaceGraph(it)
            }
        }

        viewModel.userCreds.observe(viewLifecycleOwner) {
            binding.tvUserCreds.text = "${it.userName} \n ${it.userPassword}"
        }
    }
}