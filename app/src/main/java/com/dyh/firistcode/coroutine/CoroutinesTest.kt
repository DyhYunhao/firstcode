package com.dyh.firistcode.coroutine

import android.provider.Settings
import kotlinx.coroutines.*

fun main() {
//    GlobalScope.launch {
//        println("codes run in coroutine scope")
//        delay(1500)
//        println("codes run in coroutine scope finished")
//    }
//    Thread.sleep(1000)
//
//    runBlocking {
//        println("codes run in coroutine scope")
//        delay(1500)
//        println("codes run in coroutine scope finished")
//    }
//    Thread.sleep(1000)
//
//    runBlocking {
//        launch {
//
//        }
//
//        launch {
//
//        }
//    }

//    val start = System.currentTimeMillis()
//    runBlocking {
//        repeat(100000) {
//            launch {
//                println(".")
//            }
//        }
//    }
//    val end = System.currentTimeMillis()
//    println(end - start)

//    val job = GlobalScope.launch {
//
//    }
//    job.cancel()

//    //常用写法
//    val job = Job()
//    val scope = CoroutineScope(job)
//    scope.launch {
//
//    }
//    job.cancel()
//
//    runBlocking {
//        coroutineScope {
//            launch {
//                for (i in 1..10) {
//                    println(i)
//                    delay(1000)
//                }
//            }
//        }
//        println("coroutineScope finished")
//    }
//    println("runBlocking finished")

    //async函数必须在协程作用域中才能调用，创建一个新的子协程并返回一个Deferred对象，其await方法可得到返回结果
    runBlocking {
        val res = async {
            5 + 5
        }.await()
        println(res)
    }
}

//suspend 挂起函数，挂起函数可以相互调用
suspend fun f1() {
    delay(1000)
}

//coroutineScope也是挂起函数，但是会继承外部的协程的作用域并创建一个子协程
suspend fun f2() = coroutineScope {
    launch {
        delay(1000)
    }
}

/**
 * 创建一个新的协程作用域可以使用： GlobalScope.launch, runBlocking, launch, coroutineScope
 *    GlobalScope.launch和runBlocking可以在任意地方调用
 *    GlobalScope.launch每次创建的都是顶层协程
 *    runBlocking有性能问题，如果在主线程调用可能会卡死界面
 *    coroutineScope函数可以在协程作用域或挂起函数中调用
 *    launch函数只能在协程作用域中调用
 */


