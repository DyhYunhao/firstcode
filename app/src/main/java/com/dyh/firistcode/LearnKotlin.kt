package com.dyh.firistcode

import android.content.Context
import kotlin.math.max

fun main() {
    println("Hello Kotlin！")

    //优先使用val, 当val不满足需求时再使用var

    val a = 10
    println("a = $a")

    for (i in 0 until 10 step 2) {
        println(i)
    }
    for (i in 10 downTo 1 step 2) {
        println(i)
    }

    //listOf创建的是不可变的集合，不可增添，修改或删除
    //可变集合使用mutableListOf
    val list = listOf("1", "2", "3")
    val set = mutableSetOf("1", "2")
    set.add("23")
    val map = HashMap<String, Int>()
    map["a"] = 1
    map["b"] = 2
    for ((s, n) in map) {
        println("s is $s and n is $n")
    }
}

fun largerNumber(num1: Int, num2: Int): Int {
    return max(num1, num2)
}
fun largerNumber1(num1: Int, num2: Int) = max(num1, num2)

fun largerNumber2(num1: Int, num2: Int): Int {
    //kotlin的if有返回值
    var value = if (num1 > num2) {
        num1
    } else {
        num2
    }
    return value
}
fun largerNumber3(num1: Int, num2: Int) = if (num1 > num2) num1 else num2


//obj.let {}
//with(objecet) {}
//obj.run {}
//obj.apply {}
//io.use {}

