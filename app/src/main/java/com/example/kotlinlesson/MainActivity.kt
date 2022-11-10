package com.example.kotlinlesson

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button1)

        button.setOnClickListener {
            startActivity(
                Intent(this, MainActivity2::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
// Маин активити 2 не будет добавлен в бекстек
//                .addFlasgs(Intent.FLAG_ACTIVITY_NO_HISTORY))
        }
    }
}