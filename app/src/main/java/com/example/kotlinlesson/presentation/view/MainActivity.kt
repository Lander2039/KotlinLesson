package com.example.kotlinlesson.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.ActivityMainBinding
import com.example.kotlinlesson.presentation.view.auth.LoginFragment
import com.example.kotlinlesson.presentation.view.home.HomeFragment
import com.example.kotlinlesson.presentation.view.mvp.MainPresenter
import com.example.kotlinlesson.presentation.view.mvp.MainView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(_binding!!.root)

        mainPresenter.setView(this) // передаем нашу вью

        mainPresenter.checkUserExists() //1 говорим презентеру проверить
        }

    override fun userExistsResult(userExists: Boolean) { //5 переопределяем метод интерфейса и получаем ответ из презентера
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activity_container,
            when(userExists){
                true -> HomeFragment()
                false -> LoginFragment()
            }
        )
        fragmentTransaction.commit()
    }
}
