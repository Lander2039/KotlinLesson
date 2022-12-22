package com.example.kotlinlesson.presentation.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentLoginBinding
import com.example.kotlinlesson.presentation.view.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(), LoginView {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var loginPresenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPresenter.setView(this)

        binding.btnLogin.setOnClickListener {
            loginPresenter.loginUser(
                binding.etTextName.text.toString(),
                binding.etTextPassword.text.toString()
            )
        }
    }

    override fun userLoggedIn() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, HomeFragment())
            .addToBackStack("")
            .commit()
    }
}