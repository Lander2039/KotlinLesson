package com.example.kotlinlesson.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinlesson.domain.auth.AuthInteractor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsVIewModel @Inject constructor(private val authInteractor: AuthInteractor) : ViewModel() {

    private val _nav = MutableLiveData<Unit?>()
    val nav: LiveData<Unit?> = _nav

    fun logoutUser() {
        authInteractor.logoutUser()
        _nav.value = Unit
    }
}