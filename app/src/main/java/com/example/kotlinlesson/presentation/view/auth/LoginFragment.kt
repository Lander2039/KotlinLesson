package com.example.kotlinlesson.presentation.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinlesson.App
import com.example.kotlinlesson.databinding.FragmentLoginBinding
import com.example.kotlinlesson.utils.BaseFragment
import com.example.kotlinlesson.utils.NavHelper.navigate


class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(
                binding.etTextName.text.toString(),
                binding.etTextPassword.text.toString()
            )
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null) {
                navigate(it)
                viewModel.userNavigate()
            }
        }
    }
}