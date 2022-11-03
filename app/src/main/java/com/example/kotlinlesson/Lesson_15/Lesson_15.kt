package com.example.kotlinlesson.Lesson_15

private const val ZERO = 0 //Константа. Имемя всегда в верхем регистре

fun outerFun() { //внешняя функция
    print("I'm an outer fun")
}

open class Lesson_15 {

    val country = "Belarus"
    var city = "Minsk"
    lateinit var address: String


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print("hello world")
            val kotlinClass = KotlinClass("Yura", 29)
            val kotlinClass2 = KotlinClass("country", "city", "address")

            println("${kotlinClass2.city} ${kotlinClass2.city} ${kotlinClass2.address}")
//            kotlinClass.getNameAndAge() Вызываем метод
            var string = kotlinClass.getNameAndAge()
            print(string)

            val lesson_15 = Lesson_15()
//            Lesson_15.country = "cant change"
            lesson_15.city = "Brest"
            lesson_15.address = "Smolechkova"
            println("${lesson_15.address}")

            lesson_15.returnBoolean()
            outerFun()

        }
    }

    fun emptyFun(): Unit { // Ничено не возвращает
        print("I'm am an empy fun")
    }

    fun returnBoolean(): Boolean = true
}

class KotlinClass(var name: String, var age: Int) : Parent(),
    Behavior { //Создаем конструктор, обязательно с val или var
    // Вторичный конструктор
    public var country: String = ""
    public var city: String = ""
    public var address: String = ""

    constructor() : this("", 1)
    constructor(country: String, city: String, address: String) : this() {
        this.country = country
        this.city = city
        this.address = address
    }

    //
    fun getNameAndAge(): String {
        return "\n $name $age"
    }

    override fun getHairColor() {
        super.getHairColor()
    }

    override fun getEyeColor() {
        TODO("Not yet implemented")
    }


}

open class Parent {

    open fun getHairColor() {

    }

}

interface Behavior {

    fun getEyeColor()

}