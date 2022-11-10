package com.example.kotlinlesson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val button2 = findViewById<Button>(R.id.button3)

        button2.setOnClickListener {
            startActivity(
                Intent(this, MainActivity3::class.java))
        }
    }
}