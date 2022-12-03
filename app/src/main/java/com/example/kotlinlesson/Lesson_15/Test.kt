package com.example.kotlinlesson.Lesson_15

class Test {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

val text:String = "123221sdsdsd"
            var index: Int = text.indexOf("@")
            println("$index")
        }
    }
}