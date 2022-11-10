package com.example.kotlinlesson

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val button3 = findViewById <Button>(R.id.button4)

        button3.setOnClickListener {
            startActivity(Intent("com.example.kotlinlesson.Kotlin.OPEN_ACTIVITY2"))
        }
    }
}