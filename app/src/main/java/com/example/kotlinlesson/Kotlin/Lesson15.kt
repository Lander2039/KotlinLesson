package com.example.kotlinlesson.Kotlin

class Lesson15 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val first = First()
            val first2 = First()

            val second = Second
            val second2 = Second

            println("${first.hashCode()} ${first2.hashCode()}")
            println("${second.hashCode()} ${second2.hashCode()}")

            val second3 = Second().navigate()
            val second4 = Second.walk()
        }
    }
}

class First {

    fun navigate() {
        print("navigate...")
    }
}

class Second {
    fun navigate() {
        print("Navigate...")
    }

    companion object {
        fun walk() {
            print("I'm walking")
        }
    }
}


