package com.example.kotlinlesson.presentation.view.mvp

import com.example.kotlinlesson.domain.auth.AuthInteractor
import com.example.kotlinlesson.presentation.view.MainActivity
import javax.inject.Inject

class MainPresenter @Inject constructor(private val authInteractor: AuthInteractor){

    private lateinit var mainView: MainView

    fun setView(mainActivity: MainActivity){ // инициализируем наш интерфейс и передаем класс который имплиментит интерфейс
        mainView = mainActivity
    }

    fun checkUserExists(){
        val doesUserExists = authInteractor.checkUserExists() //2 вносим в переменную результат проверки
        mainView.userExistsResult(doesUserExists)  // 4 передаем результат через метод интерфейса
    }

}