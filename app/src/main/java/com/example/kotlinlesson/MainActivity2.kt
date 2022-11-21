package com.example.kotlinlesson

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.kotlinlesson.KotlinActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "New title!"
        supportActionBar?.setIcon(R.mipmap.ic_dumbbell_foreground)

        val button2 = findViewById<Button>(R.id.button3)

        val textView2 = findViewById<TextView>(R.id.textView2)

        val data: String = intent.getStringExtra(KEY) ?: getString(R.string.NoDate)
        textView2.text = data

        button2.setOnClickListener {
            startActivity(
                Intent(this, MainActivity3::class.java)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.close -> finishAffinity()
            R.id.goBlack -> onBackPressed()
            R.id.goKotlin -> startActivity(Intent(this, KotlinActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        private const val KEY = "1"

        fun startMainActivity2(
            context: Context,
            string: String
        ) {
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra(KEY, string)
            context.startActivity(intent)
        }
    }
}