package com.example.kotlinlesson.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.auth.AuthInteractor
import com.example.kotlinlesson.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authInteractor: AuthInteractor) : ViewModel() {

    private val _userCreds = MutableLiveData<UserModel>()
    val userCreds: LiveData<UserModel> = _userCreds

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav

    fun showUserDate() {
        viewModelScope.launch {
            _userCreds.value = authInteractor.getUserCreds()
        }
    }

    fun userLogout(){
        _nav.value = R.navigation.main_graph
    }

}