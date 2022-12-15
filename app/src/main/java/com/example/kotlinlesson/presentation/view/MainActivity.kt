package com.example.kotlinlesson.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinlesson.R
import com.example.kotlinlesson.presentation.dataBinding.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activity_container, LoginFragment())
        fragmentTransaction.commit()
    }
}