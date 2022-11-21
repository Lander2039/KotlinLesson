package com.example.kotlinlesson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.kotlinlesson.KotlinActivity.Companion.kotlinActivityStart
import com.google.android.material.textfield.TextInputLayout

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val btnDisplayText = findViewById <Button>(R.id.btn_displayText)
        val editText = findViewById<EditText>(R.id.et_text)
        val editText2 = findViewById<EditText>(R.id.et_text2)
        val textView = findViewById<TextView>(R.id.tv_text)


        val layout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val layout2 = findViewById<TextInputLayout>(R.id.textInputLayout2)

        val rb1 = findViewById<RadioButton>(R.id.rb1)
        val rb2 = findViewById<RadioButton>(R.id.rb2)

        rb1.setOnClickListener{
            if(rb1.isChecked){
                rb2.isChecked = false
            } else {
                rb1.isChecked = true
            }
        }

        rb2.setOnClickListener{
            if(rb2.isChecked){
                rb1.isChecked = false
            } else {
                rb2.isChecked = true
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle("Information")
            .setMessage("I am Android")
            .setCancelable(false)
            .setPositiveButton("Ok"){dialog, _ ->
                Toast.makeText(this,"Login in progress", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("cansel"){dialog, _ ->
                dialog.cancel()

            }


        btnDisplayText.setOnClickListener {
            dialog.show()
            if(editText.text.toString().isEmpty()){
            layout.setErrorIconDrawable(R.drawable.goodicon)
                editText.error = "email can"
            } else  if (editText2.text.toString().isEmpty()){
                layout2.setErrorIconDrawable(R.drawable.goodicon)
                editText2.error = "password cant"
            }
            else
                kotlinActivityStart(this)
                textView.text = "${editText.text.toString()} ${editText2.text.toString()}"
        }


//        button3.setOnClickListener {
//            startActivity(Intent("com.example.kotlinlesson.Kotlin.OPEN_ACTIVITY2"))
//        }
    }
}