package com.example.kotlinlesson.presentation.view.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.auth.AuthInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val authInteractor: AuthInteractor) : ViewModel() {

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav

    private val _msg = MutableLiveData<String?>()
    val msg: LiveData<String?> = _msg


    fun loginUser(username: String, userPassword: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            Log.w("exceptionHandler called", exception.toString())
        }
        viewModelScope.launch(CoroutineName("with exception") + Dispatchers.Main + coroutineExceptionHandler) { // liveDate должна быть на главном потоке или использовать postValue
            try {
                launch {
                    authInteractor.loginUser(username, userPassword)
                    _nav.value =
                        R.id.action_loginFragment_to_homeFragment2 // еслит мы хотит запускать на ио потоке то нужно использовать _nav.postValue(Unit
                }

            } catch (e: Exception) {
                _msg.value = e.message.toString()
                Log.w("exception", "loginUser FAILED")
            }
        }
    }

    fun userNavigate() {
        _nav.value = null
    }
}