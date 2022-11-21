package com.example.kotlinlesson


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class KotlinActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val btn = findViewById<Button>(R.id.btn123)
        val btn2 = findViewById<Button>(R.id.btn2)

        val house = HouseBuilder.Builder
            .setStock(3)
            .setSwimpool(false)
            .build()

        val person = object {
            val name = "Yura"
            fun develop(){
                Log.w("anonyme", "called develop fun")
            }
        }

        val person2 = object : Developer {
            val name = "Yura"
            override fun develop() {
                Log.w("anonym class", "called develop fun")
            }

        }
        btn2.setOnClickListener {
            ListViewKotlinActivity.startListViewKotlinActivity(this)
        }

        btn.setOnClickListener {
            Log.w("houseBuilder", "${house.hasSwimpool()} ${house.howManyStocks()}")
            person.develop()
            person2.name
            person2.develop()
            callAnomymClass(person2)
            callAnomymClass(object :Developer{
                override fun develop() {
                    Log.w("anonym class", "called develop fun DEVELOPER")
                }
            })
            Toast.makeText(this, person.name, Toast.LENGTH_SHORT).show()
        }


    val lambda = {string:String -> Toast.makeText(this, "your text is $string",
            Toast.LENGTH_SHORT).show()}
        lambda("Set text on lambda")
        makeRequest("http://google.com"){callBackResult->
            Log.w("callBack raesult", callBackResult)

        }
        returnAnomymClass("set string in return fun").develop()
    }

private  fun  returnAnomymClass(string: String) = object {
    fun develop(){
        Log.w("anonym class", "called develop fun from returnType $string")
    }
    }

    fun callAnomymClass(developer: Developer){

    }

    fun  makeRequest (url:String, argForCallBack: (string:String) -> Unit){
        argForCallBack.invoke("callBackResult $url")
    }

    companion object{

        fun kotlinActivityStart(context: Context){
            context.startActivity(Intent(context, KotlinActivity::class.java))
        }
    }
}
interface Developer{
    fun develop(){
        Log.w("anonyme", "called develop fun")
    }
}


