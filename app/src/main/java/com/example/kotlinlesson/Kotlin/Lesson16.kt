package com.example.kotlinlesson.Kotlin

open class Lesson16 {
    //   Null safety
    val nullable: Int? = null

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val value = Value(0)
            println(value.name.toString())

            val lesson16 = Lesson16()
            var nonNullableValue: Int = 0 // не нал переменная

            val nullableValue: Int? = lesson16.nullable // нал переменная

            if (nullableValue != null) { // проверяем что не нал наша переменная
                nonNullableValue = nullableValue
            } else { // а тут нал наша нал переменная
//                nonNullableValue = nullableValue // не могу присвоить нал на нал переменной
            }

//            Проверка на нал способом .Let
            nullableValue?.let {
                nonNullableValue = it
            }

//            Elvise Operator ?:
            nonNullableValue = nullableValue ?: 0 // ?: это if else
            println("Elvise Operator ?: $nonNullableValue")
//            Nun null !!
            nonNullableValue = nullableValue!!
            println(nonNullableValue)
//            что-то.?что-то
            val outer = Outer(Inner("value in inner"))
            val value2: String = outer.inner?.value ?:""
        }
    }
}

data class Outer(val inner: Inner?)
data class Inner(val value: String)

data class Value(
    val number: Int,
    val name: String? = "have no string"
)