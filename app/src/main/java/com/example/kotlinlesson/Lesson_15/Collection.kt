package com.example.kotlinlesson.Lesson_15

class Collection {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val collectionList = listOf<String>(
                "hello",
                "bye",
                "world"
            )

//            collectionList.add()
            // mutable collection
            val collectionList2 = mutableListOf<String>()
            collectionList2.add("Hello")
            collectionList2.add("Bye")
            collectionList2.add("World")

            collectionList.forEach { words ->
                print("$words ")
            }
//            Циклы
            for (i in 1..collectionList.size) {
                print(i)
            }

            val arrayList = arrayListOf<String>(
                "hello",
                "bye",
                "world"
            )
            for (word in arrayList) {
                print(word)
            }

            for (i in 10 downTo 1 step 2) { // 10 8 6 4 2
                println("$i")
            }

            for (i in 1..10) { // 1 Hello World! 3 4 5 6 7 8 9 10
                if (i == 2) {
                    println("Hello world!")
                    continue
                }
                println(i)
            }
            val collection = Collection()
            repeat(10) {
                println(collection.getName())
            }
        }
    }

    fun getName(): String {
        return "Hello World!!!!"
    }
}

