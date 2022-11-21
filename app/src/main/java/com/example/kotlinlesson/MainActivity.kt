package com.example.kotlinlesson

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.kotlinlesson.KotlinActivity
import com.example.kotlinlesson.MainActivity2.Companion.startMainActivity2

class MainActivity : AppCompatActivity() {
    @SuppressLint()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(getString(R.string.Black))))

        val button = findViewById<Button>(R.id.button1)
        val textView = findViewById<TextView>(R.id.textView)
        val btn = findViewById<Button>(R.id.btn)
        val btn2 = findViewById<Button>(R.id.btn_Kotlin)


        button.setOnClickListener {
            startActivity(
                Intent(this, MainActivity2::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
        btn2.setOnClickListener {
            startActivity(
                Intent(this, KotlinActivity::class.java)
               )
        }

        btn.setOnClickListener {
            textView.text = getString(R.string.Hello_world)
            startMainActivity2(this,
            textView.text.toString() + getString(R.string.FromMainActivity))

        }
    }
    }