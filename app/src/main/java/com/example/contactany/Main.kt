package com.example.contactany

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
//    async {
//        greet()
//
//    }
//        async {
//            greet2()
//        }
        repeat(1000){
            async {
                if (it%2 == 0){
                    delay(4000)
                }else{
                    delay(4001)
                }
                println(it)
            }
        }


    }
}
suspend fun greet (){
    delay(10000)
    println("what's about you ?")
//    greet2()
}
suspend fun  greet2(){
    println("My name is bharat ruidas..")
}