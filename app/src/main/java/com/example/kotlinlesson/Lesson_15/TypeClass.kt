package com.example.kotlinlesson.Lesson_15

class TypeClass {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val child: Child = Child()
            val parent2 = child as Parent2 // as - жесткое преображение
            if (parent2 is Parent2) {  //is - проверка на отношение (типо условие - является или нет)
//                parent2.sleep()
                parent2.walk()
            }
            if (parent2 is Child) {
                parent2.sleep()
                parent2.walk()
            }

        }
    }
}

open class Parent2 {
    open fun walk() {
        print("Parent is walking..")
    }
}

class Child : Parent2() {
    override fun walk() {
        print("Child is walking")
    }

    fun sleep() {
        print("Child is sleeping..")
    }

}